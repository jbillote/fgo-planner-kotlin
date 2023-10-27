package com.jbillote.fgoplanner.service

import com.jbillote.fgoplanner.model.Material
import com.jbillote.fgoplanner.model.MaterialList
import com.jbillote.fgoplanner.model.MaterialResponse
import com.jbillote.fgoplanner.model.Servant
import com.jbillote.fgoplanner.model.ServantResponse
import com.jbillote.fgoplanner.model.ServantSearch
import com.jbillote.fgoplanner.model.ServantSearchResponse
import com.jbillote.fgoplanner.model.Skill

import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Repository
@Service
class ServantService (
    private val webClient: WebClient
) {

    suspend fun getServant(id: Int): Servant? {
        val s = webClient.get()
            .uri("/nice/JP/servant/$id?lang=en")
            .retrieve()
            .awaitBody<ServantResponse>()

        val portraits = arrayListOf<String?>()
        for (k in s.extraAssets["charaGraph"]!!["ascension"]!!.keys) {
            portraits.add(k.toInt() - 1, s.extraAssets["charaGraph"]!!["ascension"]!![k])
        }

        val skills = arrayListOf<Skill>()
        for (skill in s.skills) {
            skills.add(skill.num - 1, Skill(skill.name, skill.icon))
        }

        val appends = arrayListOf<Skill>()
        for (append in s.appends) {
            appends.add(Skill(append.skill.name, append.skill.icon))
        }

        return Servant(
            id = s.id,
            name = s.name,
            icon = s.extraAssets["faces"]!!["ascension"]!!["1"],
            portraits = portraits,
            skills = skills,
            appends = appends,
            ascensionMaterials = processMaterialList(s.ascensionMaterials),
            appendMaterials = processMaterialList(s.appendMaterial),
            skillMaterials = processMaterialList(s.skillMaterials),
            classIcon = "https://static.atlasacademy.io/JP/ClassIcons/class${3.coerceAtMost(s.rarity)}_${s.classId}.png"
        )
    }

    // TODO: Make non-blocking
    fun searchServant(query: String): List<ServantSearch> {
        val servants = ArrayList<ServantSearch>()

        val s = webClient.get()
            .uri("/nice/JP/servant/search?name=$query&lang=en")
            .retrieve()
            .bodyToFlux(ServantSearchResponse::class.java)
            .collectList()
            .block()

        if (s != null) {
            for (servant in s) {
                servants.add(ServantSearch(
                    id = servant.id,
                    name = servant.name,
                    icon = servant.extraAssets["faces"]!!["ascension"]!!["1"],
                    classIcon = "https://static.atlasacademy.io/JP/ClassIcons/class${3.coerceAtMost(servant.rarity)}_${servant.classId}.png"
                ))
            }
        }

        return servants
    }

    private fun processMaterialList(list : Map<String, MaterialResponse>): List<MaterialList> {
        val mats = ArrayList<MaterialList>()

        for (k in list.keys) {
            val items = list[k]?.item
            val processedItems = ArrayList<Material>()

            for (i in items!!) {
                processedItems.add(
                    Material(
                        id = i.item.id,
                        name = i.item.name,
                        icon = i.item.icon,
                        amount = i.amount
                )
                )
            }

            mats.add(MaterialList(
                materials = processedItems,
                qp = list[k]!!.qp
            ))
        }

        return mats
    }
}

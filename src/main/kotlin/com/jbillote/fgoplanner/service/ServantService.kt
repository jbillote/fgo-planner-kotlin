package com.jbillote.fgoplanner.service

import com.jbillote.fgoplanner.model.Material
import com.jbillote.fgoplanner.model.MaterialList
import com.jbillote.fgoplanner.model.MaterialResponse
import com.jbillote.fgoplanner.model.Servant
import com.jbillote.fgoplanner.model.ServantResponse
import com.jbillote.fgoplanner.model.ServantSearch
import com.jbillote.fgoplanner.model.ServantSearchResponse

import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Repository
@Service
class ServantService (
    private val webClient: WebClient
) {

    fun getServant(id: Int): Servant? {
        val s = webClient.get()
            .uri("/nice/JP/servant/$id?lang=en")
            .retrieve()
            .bodyToMono(ServantResponse::class.java)
            .block()

        return Servant(
            id = s!!.id,
            name = s.name,
            icon = s.extraAssets["faces"]!!["ascension"]!!["1"],
            ascensionMaterials = processMaterialList(s.ascensionMaterials),
            appendMaterials = processMaterialList(s.appendMaterial),
            skillMaterials = processMaterialList(s.skillMaterials)
        )
    }

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
                    icon = servant.extraAssets["faces"]!!["ascension"]!!["1"]
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

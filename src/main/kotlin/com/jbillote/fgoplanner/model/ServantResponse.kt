package com.jbillote.fgoplanner.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ServantResponse (
    @JsonProperty("id")
    val id:                 Int,

    @JsonProperty("name")
    val name:               String,

    @JsonProperty("classId")
    val classId:            Int,

    @JsonProperty("rarity")
    val rarity:             Int,

    @JsonProperty("extraAssets")
    val extraAssets:         Map<String, Map<String, Map<String, String>>>,

    @JsonProperty("skills")
    val skills:             List<SkillResponse>,

    @JsonProperty("appendPassive")
    val appends:            List<AppendResponse>,

    @JsonProperty("ascensionMaterials")
    val ascensionMaterials: Map<String, MaterialResponse>,

    @JsonProperty("skillMaterials")
    val skillMaterials:     Map<String, MaterialResponse>,

    @JsonProperty("appendSkillMaterials")
    val appendMaterial:     Map<String, MaterialResponse>
)

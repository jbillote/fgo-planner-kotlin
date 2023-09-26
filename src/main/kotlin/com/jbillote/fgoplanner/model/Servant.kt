package com.jbillote.fgoplanner.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Servant(
    @JsonProperty("id")
    val id:                 Int?,

    @JsonProperty("name")
    val name:               String?,

    @JsonProperty("classIcon")
    val classIcon:          String?,

    @JsonProperty("icon")
    val icon:               String?,

    @JsonProperty("ascensionMaterials")
    val ascensionMaterials: List<MaterialList>?,

    @JsonProperty("skillMaterials")
    val skillMaterials:     List<MaterialList>?,

    @JsonProperty("appendMaterials")
    val appendMaterials:    List<MaterialList>?
)

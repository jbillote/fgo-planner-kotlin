package com.jbillote.fgoplanner.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MaterialList (
    @JsonProperty("materials")
    val materials: List<Material>,

    @JsonProperty("qp")
    val qp: Int
)

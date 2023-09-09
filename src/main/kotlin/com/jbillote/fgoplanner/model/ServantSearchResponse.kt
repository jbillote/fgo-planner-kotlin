package com.jbillote.fgoplanner.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ServantSearchResponse(
    @JsonProperty("id")
    val id:          Int,

    @JsonProperty("name")
    val name:        String,

    @JsonProperty("extraAssets")
    val extraAssets: Map<String, Map<String, Map<String, String>>>
)

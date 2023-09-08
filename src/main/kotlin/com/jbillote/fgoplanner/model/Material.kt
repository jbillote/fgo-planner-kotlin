package com.jbillote.fgoplanner.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Material (
    @JsonProperty("name")
    val name:   String?,

    @JsonProperty("icon")
    val icon:   String,

    @JsonProperty("amount")
    val amount: Int?
)

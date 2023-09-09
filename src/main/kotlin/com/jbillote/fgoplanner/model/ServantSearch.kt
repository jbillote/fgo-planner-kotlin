package com.jbillote.fgoplanner.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ServantSearch(
    @JsonProperty("id")
    val id:                 Int?,

    @JsonProperty("name")
    val name:               String?,

    @JsonProperty("icon")
    val icon:               String?
)

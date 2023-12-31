package com.jbillote.fgoplanner.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Skill(
    @JsonProperty("name")
    val name: String?,

    @JsonProperty("icon")
    val icon: String?
)

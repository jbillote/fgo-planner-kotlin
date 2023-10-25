package com.jbillote.fgoplanner.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SkillResponse (
    @JsonProperty("num")
    val num:  Int,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("icon")
    val icon: String
)

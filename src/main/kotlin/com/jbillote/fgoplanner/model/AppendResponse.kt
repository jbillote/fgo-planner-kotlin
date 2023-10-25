package com.jbillote.fgoplanner.model

import com.fasterxml.jackson.annotation.JsonProperty

data class AppendResponse(
    @JsonProperty("skill")
    val skill: AppendSkillResponse
)

data class AppendSkillResponse(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("icon")
    val icon: String
)

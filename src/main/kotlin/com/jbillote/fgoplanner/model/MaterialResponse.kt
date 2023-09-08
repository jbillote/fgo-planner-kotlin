package com.jbillote.fgoplanner.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MaterialResponse(
    @JsonProperty("items")
    val item: List<ItemResponse>,

    @JsonProperty("qp")
    val qp:   Int
)

data class ItemResponse(
    @JsonProperty("item")
    val item:   ItemDetailsResponse,

    @JsonProperty("amount")
    val amount: Int
)

data class ItemDetailsResponse(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("icon")
    val icon: String
)

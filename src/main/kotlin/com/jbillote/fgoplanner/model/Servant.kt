package com.jbillote.fgoplanner.model

data class Servant(
    val name:          String?,
    val icon:          String,
    val ascensionMats: List<List<Material>>,
    val skillMats:     List<List<Material>>,
    val appendMats:    List<List<Material>>
)

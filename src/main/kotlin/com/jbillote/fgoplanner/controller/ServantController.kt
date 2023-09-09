package com.jbillote.fgoplanner.controller

import com.jbillote.fgoplanner.service.ServantService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/fgo/servant")
class ServantController (
    @Autowired
    private val servantService: ServantService
) {

    @GetMapping("/{id}")
    suspend fun findServantById(@PathVariable id: Int) = servantService.getServant(id)

    @GetMapping("/search/{query}")
    fun searchForServant(@PathVariable query: String) = servantService.searchServant(query)
}

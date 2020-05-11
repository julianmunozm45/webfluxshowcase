package com.julianmunozm45.webfluxshowcase.router

import com.julianmunozm45.webfluxshowcase.handler.ProfileHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class ProfileRouter(private val handler: ProfileHandler) {

    @Bean
    fun routes() = router {
        GET("/profiles", handler::all)
        GET("/profiles/{id}", handler::getById)
        DELETE("/profiles/{id}", handler::deleteById)
        POST("/profiles", handler::create)
        PUT("/profiles/{id}", handler::create)
    }
}
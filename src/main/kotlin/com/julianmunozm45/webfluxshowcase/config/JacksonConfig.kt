package com.julianmunozm45.webfluxshowcase.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.bson.types.ObjectId
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JacksonConfig {

    @Bean
    fun jsonObjectMapper(): ObjectMapper {
        val objectIdModule = SimpleModule()
        objectIdModule.addSerializer(ObjectId::class.java, ObjectIdSerializer())
        return ObjectMapper().registerModule(objectIdModule)
    }
}
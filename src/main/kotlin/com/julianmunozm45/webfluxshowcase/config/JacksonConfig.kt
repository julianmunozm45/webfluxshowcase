package com.julianmunozm45.webfluxshowcase.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import org.bson.types.ObjectId
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun jsonObjectMapper(): ObjectMapper {
        val objectIdModule = SimpleModule()
                .run {
                    addSerializer(ObjectId::class.java, ObjectIdSerializer())
                    addDeserializer(ObjectId::class.java, ObjectIdDeserializer())
                }

        return ObjectMapper().registerModule(objectIdModule)
    }
}
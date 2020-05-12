package com.julianmunozm45.webfluxshowcase.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.bson.types.ObjectId
import org.springframework.context.annotation.Configuration
import java.io.IOException

@Configuration
class ObjectIdSerializer : JsonSerializer<ObjectId>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(value: ObjectId, jgen: JsonGenerator, provider: SerializerProvider) {
        jgen.writeString(value.toString())
    }
}
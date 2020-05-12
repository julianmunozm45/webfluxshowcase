package com.julianmunozm45.webfluxshowcase.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import org.bson.types.ObjectId
import org.springframework.stereotype.Component

@Component
class ObjectIdDeserializer : JsonDeserializer<ObjectId>() {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ObjectId {
        val oid: JsonNode? = (p.readValueAsTree() as JsonNode).get("id")
        return ObjectId(oid?.asText() ?: "")
    }
}
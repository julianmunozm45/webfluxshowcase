package com.julianmunozm45.webfluxshowcase.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Profile(
        @BsonId
        val id: ObjectId = ObjectId.get(),
        val email: String = ""
)
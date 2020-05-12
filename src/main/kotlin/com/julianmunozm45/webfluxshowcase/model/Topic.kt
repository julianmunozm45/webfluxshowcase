package com.julianmunozm45.webfluxshowcase.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Topic(
        @Id val id: ObjectId? = null
)
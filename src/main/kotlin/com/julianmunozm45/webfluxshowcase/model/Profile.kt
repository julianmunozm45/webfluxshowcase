package com.julianmunozm45.webfluxshowcase.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Profile(
        @Id val id: ObjectId? = null,
        val email: String
) {

    override fun toString(): String {
        return super.toString()
    }
}
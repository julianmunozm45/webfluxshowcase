package com.julianmunozm45.webfluxshowcase.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Profile(
        @Id val id: String? = null,
        val email: String
)
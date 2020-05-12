package com.julianmunozm45.webfluxshowcase.repository

import com.julianmunozm45.webfluxshowcase.model.Profile
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ProfileRepository : ReactiveMongoRepository<Profile, ObjectId>
package com.julianmunozm45.webfluxshowcase.service

import com.julianmunozm45.webfluxshowcase.event.ProfileCreatedEvent
import com.julianmunozm45.webfluxshowcase.model.Profile
import com.julianmunozm45.webfluxshowcase.repository.ProfileRepository
import org.bson.types.ObjectId
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProfileService(
        private val profileRepository: ProfileRepository,
        private val publisher: ApplicationEventPublisher
) : ProfileRepository by profileRepository {

    fun update(id: ObjectId, email: String): Mono<Profile> {
        return profileRepository
                .findById(id)
                .map { p -> Profile(p.id, email) }
                .flatMap { p -> profileRepository.save(p) }
    }

    fun delete(id: ObjectId): Mono<Profile> {
        return profileRepository
                .findById(id)
                .flatMap { p -> profileRepository.deleteById(id).thenReturn(p) }
    }

    fun create(email: String): Mono<Profile> {
        return profileRepository
                .save(Profile(email = email))
                .doOnNext { profile -> publisher.publishEvent(ProfileCreatedEvent(profile)) }
    }
}
package com.julianmunozm45.webfluxshowcase.handler

import com.julianmunozm45.webfluxshowcase.model.Profile
import com.julianmunozm45.webfluxshowcase.service.ProfileService
import org.reactivestreams.Publisher
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.net.URI

@Component
class ProfileHandler(private val profileService: ProfileService) {

    fun getById(req: ServerRequest): Mono<ServerResponse> = profileService
            .findById(id(req))
            .flatMap { defaultReadResponse(it.toMono()) }

    fun all(req: ServerRequest): Mono<ServerResponse> = profileService
            .findAll()
            .toReadResponse()

    fun deleteById(req: ServerRequest): Mono<ServerResponse> = profileService
            .delete(id(req))
            .toReadResponse()

    fun updateById(req: ServerRequest): Mono<ServerResponse> = req
            .bodyToFlux(Profile::class.java)
            .flatMap { p -> profileService.update(id(req), p.email) }
            .toReadResponse()

    fun create(req: ServerRequest): Mono<ServerResponse> = req
            .bodyToFlux(Profile::class.java)
            .flatMap { toWrite -> profileService.create(toWrite.email) }
            .toWriteResponse()

    private fun defaultReadResponse(profiles: Publisher<Profile>): Mono<ServerResponse> = ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(profiles, Profile::class.java)

    private fun Publisher<Profile>.toReadResponse() = defaultReadResponse(this)

    private fun defaultWriteResponse(profiles: Publisher<Profile>): Mono<ServerResponse> = Mono
            .from(profiles)
            .flatMap { p ->
                ServerResponse
                        .created(URI.create("/profiles/" + p.id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
            }

    private fun Publisher<Profile>.toWriteResponse() = defaultWriteResponse(this)

    private fun id(req: ServerRequest) = req.pathVariable("id")
}
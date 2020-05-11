package com.julianmunozm45.webfluxshowcase.config

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.julianmunozm45.webfluxshowcase.publisher.ProfileCreatedEventPublisher
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Flux
import java.lang.RuntimeException
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Configuration
class WebSocketConfiguration {

    @Bean
    fun executor(): Executor = Executors.newSingleThreadExecutor()

    @Bean
    fun handlerMapping(wsh: WebSocketHandler) = SimpleUrlHandlerMapping(
            mapOf("/ws/profiles" to wsh), 10
    )

    @Bean
    fun webSocketHandlerAdapter(): WebSocketHandlerAdapter = WebSocketHandlerAdapter()

    @Bean
    fun webSocketHandler(objectMapper: ObjectMapper, eventPublisher: ProfileCreatedEventPublisher): WebSocketHandler {
        val publish = Flux
                .create(eventPublisher)
                .share()

        return WebSocketHandler { session ->
            publish
                    .map { evt ->
                        try {
                            objectMapper.writeValueAsString(evt.source)
                        }
                        catch (e: JsonProcessingException) {
                            throw RuntimeException(e)
                        }
                    }
                    .map { str ->
                        logger.info("sending $str")
                        session.textMessage(str)
                    }
                    .let(session::send)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(WebSocketConfiguration::class.java)
    }
}
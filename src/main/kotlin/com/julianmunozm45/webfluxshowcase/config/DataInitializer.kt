package com.julianmunozm45.webfluxshowcase.config

import com.julianmunozm45.webfluxshowcase.model.Profile
import com.julianmunozm45.webfluxshowcase.repository.ProfileRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(private val profileRepository: ProfileRepository) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        profileRepository.count()
                .filter { it == 0L }
                .map {
                    profileRepository.insert(Profile(email = "julian@webflux.com"))
                }
                .then()
                .block()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DataInitializer::class.java)
    }
}
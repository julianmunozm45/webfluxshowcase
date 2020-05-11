package com.julianmunozm45.webfluxshowcase.event

import com.julianmunozm45.webfluxshowcase.model.Profile
import org.springframework.context.ApplicationEvent

class ProfileCreatedEvent(source: Profile) : ApplicationEvent(source)
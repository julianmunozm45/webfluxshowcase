package com.julianmunozm45.webfluxshowcase.publisher

import com.julianmunozm45.webfluxshowcase.event.ProfileCreatedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.util.ReflectionUtils
import reactor.core.publisher.FluxSink
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.function.Consumer

@Component
class ProfileCreatedEventPublisher(
        private val executor: Executor
) : ApplicationListener<ProfileCreatedEvent>, Consumer<FluxSink<ProfileCreatedEvent>>{

    private final val queue = LinkedBlockingQueue<ProfileCreatedEvent>()

    override fun onApplicationEvent(event: ProfileCreatedEvent) {
        queue.offer(event)
    }

    override fun accept(sink: FluxSink<ProfileCreatedEvent>) {
        executor.execute {
            while (true)
                try {
                    val event = queue.take()
                    sink.next(event)
                }
                catch (e: InterruptedException) {
                    ReflectionUtils.rethrowException(e)
                }
        }
    }
}
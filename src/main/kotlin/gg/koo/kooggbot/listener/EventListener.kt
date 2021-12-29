package gg.koo.kooggbot.listener

import discord4j.core.event.domain.Event
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class EventListener: Listener<Event> {

    override fun getEventType(): Class<Event> {
        return Event::class.java
    }

    override fun execute(event: Event): Mono<Any> {
        println(event.javaClass)

        return Mono.empty()
    }
}
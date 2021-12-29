package gg.koo.kooggbot.listener

import discord4j.core.event.domain.Event
import discord4j.core.event.domain.lifecycle.ReadyEvent
import discord4j.core.event.domain.message.MessageCreateEvent
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class EventListener: Listener<Event> {

    override fun getEventType(): Class<Event> {
        return Event::class.java
    }

    override fun execute(event: Event): Mono<Any> {
        when (event) {
            is ReadyEvent -> println("ㅇㅇㅇ")
            is MessageCreateEvent -> println("Message")
            else -> println("모름")
        }
        return Mono.empty()
    }
}
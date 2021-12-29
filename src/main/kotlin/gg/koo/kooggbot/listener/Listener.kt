package gg.koo.kooggbot.listener

import discord4j.core.event.domain.Event
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

interface Listener<T: Event> {

    fun getEventType(): Class<T>
    fun execute(event: Event): Mono<Any>

    fun handleError(error: Throwable): Mono<Any> {
        val log = LoggerFactory.getLogger(Listener::class.java)
        log.error("Unable to process ${getEventType().simpleName}", error)
        return Mono.empty()
    }
}
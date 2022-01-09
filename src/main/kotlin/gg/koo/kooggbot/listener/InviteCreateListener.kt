package gg.koo.kooggbot.listener

import discord4j.core.event.domain.Event
import discord4j.core.event.domain.InviteCreateEvent
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.reactor.asFlux
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Component
class InviteCreateListener: Listener<InviteCreateEvent> {

    override fun getEventType(): Class<InviteCreateEvent> {
        return InviteCreateEvent::class.java
    }

    override fun execute(event: Event): Mono<Any> {
        val e = event as InviteCreateEvent

        (1..10).asFlow().asFlux()
            .doOnNext {
                println(it)
            }
            .log()
            .subscribe(
                {
                    println(it)
                },
                {
                    println(it.message)
                },
                {
                    println("Complete")
                })

        val ints = Flux.range(1, 4) // (1)
            .map { i: Int ->  // (2)
                if (i <= 3) return@map i // (3)
                throw RuntimeException("Got to 4") // (4)
            }
        ints.subscribe(
            { i: Int? -> println(i) }
        )  // (5)
        { error: Throwable -> System.err.println("Error: $error") }

//        e.guild.block()!!.invites.asFlow()
//            .collect()
//
//        e.guild.block()
//            ?.createVoiceChannel(
//                VoiceChannelCreateSpec.builder()
//                    .name("dd")
//                    .parentId(Snowflake.of(925033253029707798))
//                    .userLimit(4)
//                    .addAllPermissionOverwrites(listOf(
//                        PermissionOverwrite.forMember(
//                            Snowflake.of(257825765528174593),
//                            PermissionSet.of(Permission.VIEW_CHANNEL, Permission.MANAGE_CHANNELS),
//                            PermissionSet.none()
//                        )
//                    )).build()
//            )
        return Mono.empty()
    }
}
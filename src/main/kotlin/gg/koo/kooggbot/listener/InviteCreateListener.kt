package gg.koo.kooggbot.listener

import discord4j.core.event.domain.Event
import discord4j.core.event.domain.InviteCreateEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class InviteCreateListener: Listener<InviteCreateEvent> {

    override fun getEventType(): Class<InviteCreateEvent> {
        return InviteCreateEvent::class.java
    }

    override fun execute(event: Event): Mono<Any> {
        val e = event as InviteCreateEvent

        (1..10).asFlow().flowOn(Dispatchers.IO)
            .asFlux()
            .subscribe {
                println(it)
            }
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
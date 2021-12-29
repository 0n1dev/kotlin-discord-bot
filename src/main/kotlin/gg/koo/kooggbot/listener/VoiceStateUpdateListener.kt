package gg.koo.kooggbot.listener

import discord4j.common.util.Snowflake
import discord4j.core.`object`.PermissionOverwrite
import discord4j.core.event.domain.Event
import discord4j.core.event.domain.VoiceStateUpdateEvent
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.core.spec.VoiceChannelCreateSpec
import discord4j.rest.util.Permission
import discord4j.rest.util.PermissionSet
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class VoiceStateUpdateListener: Listener<VoiceStateUpdateEvent> {

    override fun getEventType(): Class<VoiceStateUpdateEvent> {
        return VoiceStateUpdateEvent::class.java
    }

    override fun execute(event: Event): Mono<Any> {
        if (event is VoiceStateUpdateEvent) {
            val current = event.current

            current.channel.block()?.id?.let {
                if (it.equals(925033253029707800)) {
                    current.guild.subscribe{
                        it.createVoiceChannel(
                            VoiceChannelCreateSpec.builder()
                                .name("dd")
                                .parentId(Snowflake.of(925033253029707798))
                                .userLimit(4)
                                .addAllPermissionOverwrites(listOf(
                                    PermissionOverwrite.forMember(
                                        Snowflake.of(257825765528174593),
                                        PermissionSet.of(Permission.VIEW_CHANNEL, Permission.MANAGE_CHANNELS),
                                        PermissionSet.none()
                                    )
                                )).build()
                        ).subscribe()
                    }
                }
            }
        }

        return Mono.empty()
    }
}
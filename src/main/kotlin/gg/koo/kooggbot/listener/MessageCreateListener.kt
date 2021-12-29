package gg.koo.kooggbot.listener

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import discord4j.common.util.Snowflake
import discord4j.core.`object`.Invite
import discord4j.core.event.domain.Event
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.core.spec.BanQuerySpec
import discord4j.rest.http.client.ClientException
import kotlinx.coroutines.reactor.mono
import org.springframework.stereotype.Component
import reactor.core.publisher.Hooks
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class MessageCreateListener: Listener<MessageCreateEvent> {

    override fun getEventType(): Class<MessageCreateEvent> {
        return MessageCreateEvent::class.java
    }

    override fun execute(event: Event): Mono<Any> {
        try {
            val e = event as MessageCreateEvent

//            val obj = ObjectMapper();
//            obj.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
//            obj.registerModule(JavaTimeModule())
//            println(obj.writeValueAsString(e))

            val inviteCode = getInviteCode(e)

            if (!inviteCode.isNullOrBlank()) {
                validationInviteUrl(e, inviteCode)
            }
        } catch (e: ClientException) {
            println(e.message)
        }

        return Mono.empty()
    }

    private fun validationInviteUrl(e: MessageCreateEvent, inviteCode: String) {
        e.client.getInvite(inviteCode)
            .subscribe (
                function(e)
            ) {
                e.message.delete()
                    .subscribe()
            }
    }

    private fun function(e: MessageCreateEvent): (t: Invite) -> Unit =
        {
            if (it.guildId.get() != Snowflake.of(925033252580896768)) {
                e.message.delete().subscribe()
                e.member.get()
                    .ban(
                        BanQuerySpec.create()
                            .withReason("타 디스코드 홍보")
                            .withDeleteMessageDays(1)
                    )
                    .subscribe()
            }
        }

    private fun getInviteCode(e: MessageCreateEvent): String? {
        val inviteUrlReg =
            """(https?://)?(www.)?(discord.(gg|io|me|li)|discordapp.com/invite)/(.+[a-zA-Z0-6])""".toRegex()
        val matchResult = inviteUrlReg.find(e.message.content)

        return matchResult?.groupValues?.get(5)
    }


}
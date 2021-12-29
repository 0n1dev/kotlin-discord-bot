package gg.koo.kooggbot.config

import discord4j.core.DiscordClient
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.Event
import discord4j.gateway.intent.Intent
import discord4j.gateway.intent.IntentSet
import gg.koo.kooggbot.listener.EventListener
import gg.koo.kooggbot.listener.Listener
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Hooks
import reactor.kotlin.core.publisher.toMono

@Configuration
class BotConfiguration(
    private val listeners: List<Listener<*>>
) {

    @Value("\${token}")
    lateinit var token: String

    @Bean
    fun gatewayDiscordClient(): GatewayDiscordClient? {
        println(token)
//        Hooks.onErrorDropped {
//            it
//        }
        val client = DiscordClient.create(token)
            .gateway()
            .setEnabledIntents(
                IntentSet.of(
                    Intent.GUILDS,
                    Intent.GUILD_MESSAGES,
                    Intent.GUILD_MEMBERS,
                    Intent.GUILD_INVITES,
                    Intent.GUILD_VOICE_STATES,
                    Intent.GUILD_MESSAGE_REACTIONS))
            .login()
            .block()

        listeners.forEach {
            client!!.on(it.getEventType())
                .flatMap(it::execute)
                .onErrorResume(it::handleError)
                .subscribe()
        }
        return client
    }
}
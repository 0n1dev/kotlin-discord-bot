//package gg.koo.kooggbot.config
//
//import discord4j.core.DiscordClient
//import discord4j.core.GatewayDiscordClient
//import discord4j.core.event.EventDispatcher
//import discord4j.core.event.domain.message.MessageCreateEvent
//import discord4j.gateway.intent.Intent
//import discord4j.gateway.intent.IntentSet
//import gg.koo.kooggbot.listener.Listener
//import kotlinx.coroutines.CoroutineName
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.reactive.asFlow
//import kotlinx.coroutines.reactor.mono
//import org.reactivestreams.Publisher
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
////@Configuration
//class DiscordConfig(
//    private val listeners: List<Listener<*>>
//) {
//
//    @Bean
//    fun initClient(): GatewayDiscordClient? {
//        return DiscordClient.create("OTI1MDM1MzcwMDAzOTgwMzU4.YcnQUg.HSIu1Nes5lSdagav7K6L6nBg7rA")
//            .gateway()
//            .setEnabledIntents(
//                IntentSet.of(
//                    Intent.GUILDS,
//                Intent.GUILD_MESSAGES,
//                Intent.GUILD_MEMBERS,
//                Intent.GUILD_INVITES,
//                Intent.GUILD_VOICE_STATES,
//                Intent.GUILD_MESSAGE_REACTIONS))
//            .withEventDispatcher { dispatcher ->
//                eventDispatcher(dispatcher)
//            }
//            .login()
//            .block()
//    }
//
//    fun eventDispatcher(dispatcher: EventDispatcher): Publisher<*>? {
//        return mono(CoroutineName("EventDispatcherCoroutine")) {
//            listeners.forEach { listener ->
//                initListener(listener, dispatcher)
//            }
//        }
//    }
//
//    private fun CoroutineScope.initListener(
//        listener: Listener<*>,
//        dispatcher: EventDispatcher
//    ) {
//        println(listener)
//        launch(CoroutineName("${listener.javaClass.simpleName}Coroutine")) {
//            dispatcher.on(listener.getEvent())
//                .flatMap(listener::onReady)
//                .onErrorResume(listener::handleError)
//                .subscribe();
//        }
//    }
//}
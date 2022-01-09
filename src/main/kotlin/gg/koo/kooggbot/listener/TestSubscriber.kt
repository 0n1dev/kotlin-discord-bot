package gg.koo.kooggbot.listener

import reactor.core.publisher.BaseSubscriber



class TestSubscriber<T> : BaseSubscriber<T>() {

    override fun hookOnComplete() {
        super.hookOnComplete()
    }

    override fun hookOnSubscribe(subscription: Subscription?) {
        println("Subscribed")
        request(1)
    }

    public override fun hookOnNext(value: T) {
        println(value)
        request(1)
    }
}
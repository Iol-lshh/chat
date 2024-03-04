package org.lshh.chat.infra

import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.util.concurrent.ConcurrentHashMap

abstract class MessageQueue<T : Any>(
        protected val fluxMap: ConcurrentHashMap<Long, Flux<T>> = ConcurrentHashMap(),
        protected val sinkMap: ConcurrentHashMap<Long, FluxSink<T>> = ConcurrentHashMap()
) {
    private fun newQueue(id: Long) {
        val flux = Flux.create<T> { sink ->
            sinkMap[id] = sink
        }.publish().autoConnect()
        fluxMap[id] = flux
        flux.subscribe()
    }

    fun publish(id:Long, message:T){
        if(sinkMap[id]==null){
            newQueue(id)
        }
        sinkMap[id]?.next(message)
    }

    fun subscribe(id: Long): Flux<T> {
        if(fluxMap[id]==null){
            newQueue(id)
        }
        return fluxMap[id] ?: Flux.empty()
    }
}
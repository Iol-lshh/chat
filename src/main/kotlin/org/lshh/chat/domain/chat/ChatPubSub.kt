package org.lshh.chat.domain.chat

import reactor.core.publisher.Flux

interface ChatPubSub {
    fun publish(chat: Chat)
    fun subscribe(roomId: Long): Flux<Chat>
}
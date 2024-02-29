package org.lshh.chat.infra.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.ChatPubSub
import reactor.core.publisher.Flux

class ChatPubSubImplement(val messageQueue:ChatMessageQueue): ChatPubSub {
    override fun publish(chat: Chat) {
        TODO("Not yet implemented")
    }

    override fun subscribe(roomId: Long): Flux<Chat> {
        TODO("Not yet implemented")
    }

}
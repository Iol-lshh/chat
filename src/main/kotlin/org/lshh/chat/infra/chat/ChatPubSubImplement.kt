package org.lshh.chat.infra.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.ChatPubSub
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class ChatPubSubImplement(val mq:ChatMessageQueue): ChatPubSub {
    override fun publish(chat: Chat) {
        mq.publish(chat.room, chat)
    }

    override fun subscribe(roomId: Long): Flux<Chat> {
        return mq.subscribe(roomId)
    }

}
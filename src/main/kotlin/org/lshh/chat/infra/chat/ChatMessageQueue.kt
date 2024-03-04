package org.lshh.chat.infra.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.infra.MessageQueue
import org.springframework.stereotype.Component

@Component
class ChatMessageQueue: MessageQueue<Chat>() {

}
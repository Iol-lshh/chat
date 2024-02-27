package org.lshh.chat.controller.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.ChatService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(val chatService: ChatService) {

    @MessageMapping("/send")
    @SendTo("/topic/chats")
    fun send(chat: Chat): Int {
        return chatService.send(chat)
    }

    @GetMapping("/chats")
    fun list(userId: Long): List<Chat> {
        return chatService.readChats(userId)
    }
}
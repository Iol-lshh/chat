package org.lshh.chat.controller.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.ChatComand
import org.lshh.chat.domain.chat.ChatService
import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatController(val chatService: ChatService, val simpMessagingTemplate: SimpMessagingTemplate ) {
    private val log = LoggerFactory.getLogger(this.javaClass)!!

    @PostMapping("/send")
    fun send(@RequestBody chat: ChatComand): Int {
        log.info("send {}",chat.contents)
        val newChat = chatService.save(chat)
        simpMessagingTemplate.convertAndSend("/sub/chat", newChat);
        return newChat?.let { 1 } ?: run{0}
    }

    @GetMapping("/{userId}/list")
    fun list(@PathVariable userId: Long): List<Chat> {
        log.info("list {}",userId)
        return chatService.readChats(userId)
    }
}
package org.lshh.chat.controller.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.ChatComand
import org.lshh.chat.domain.chat.ChatService
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatController(val chatService: ChatService) {
    private val log = LoggerFactory.getLogger(this.javaClass)!!

    @MessageMapping("/send")
    fun sendBySocket(chat: ChatComand): Int {
        log.info("sendBySocket {}",chat.contents)
        return chatService.send(chat)
    }

    @PostMapping("/send")
    fun send(@RequestBody chat: ChatComand): Int {
        log.info("send {}",chat.contents)
        return chatService.send(chat)
    }

    @GetMapping("/{userId}/list")
    fun list(@PathVariable userId: Long): List<Chat> {
        log.info("list {}",userId)
        return chatService.readChats(userId)
    }

    @MessageMapping("/list")
    @SendTo("/chat/{userId}/list")
    fun listBySocket(@PathVariable userId: Long): List<Chat> {
        log.info("listBySocket {}",userId)
        return chatService.readChats(userId)
    }
}
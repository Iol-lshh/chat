package org.lshh.chat.controller.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.ChatComand
import org.lshh.chat.domain.chat.ChatService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/chat")
class ChatController(val chatService: ChatService, val simpMessagingTemplate: SimpMessagingTemplate ) {
    private val log = LoggerFactory.getLogger(this.javaClass)!!

    @PostMapping(value=["/send"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun send(@RequestBody chat: ChatComand): Mono<Int> {
        log.info("send {}",chat.contents)
        return chatService
                .save(chat)
                .doOnSuccess { newChat -> simpMessagingTemplate.convertAndSend("/sub/chat/", newChat) }
                .map { 1 }
    }

    // todo...???
    @GetMapping(value = ["/stream/{userId}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun stream(@PathVariable userId: Long): Flux<Chat> {
        return chatService.streamChats(userId)
    }

    @GetMapping(value = ["/{userId}/list"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun list(@PathVariable userId: Long?): ResponseEntity<Flux<List<Chat>>> {
        log.info("list {}", userId)
        return ResponseEntity.ok().body(userId?.let { chatService.readChats(it) })
    }

}
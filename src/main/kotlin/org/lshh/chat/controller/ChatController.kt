package org.lshh.chat.controller

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.ChatService
import org.lshh.chat.domain.chat.dto.ChatCommand
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/chat")
class ChatController(
        private val chatService: ChatService
) {

    private val log = LoggerFactory.getLogger(this.javaClass)!!

    @PostMapping("/send")
    fun send(@RequestBody command: ChatCommand): Int {
        log.info("send {}",command.contents)
        val newChat = chatService.unicast(command)
        return newChat.id?.let { 1 } ?: run{0}
    }

    @GetMapping("/{roomId}/list")
    fun list(@PathVariable roomId: Long): List<Chat> {
        log.info("list {}",roomId)
        return chatService.list(roomId)
    }

    @GetMapping(value = ["/stream/{roomId}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun stream(@PathVariable roomId: Long): Flux<Chat> {
        return chatService.subscribe(roomId)    // subscribe 해야함
    }
}
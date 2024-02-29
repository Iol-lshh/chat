package org.lshh.chat.controller

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.dto.ChatCommand
import org.lshh.chat.domain.chat.ChatService
import org.lshh.chat.domain.room.RoomService
import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatController(
        val chatService: ChatService,
        val roomService: RoomService,
        val simpMessagingTemplate: SimpMessagingTemplate
) {
    private val log = LoggerFactory.getLogger(this.javaClass)!!

    @PostMapping("/send")
    fun send(@RequestBody command: ChatCommand): Int {
        log.info("send {}",command.contents)
        val newChat = chatService.unicast(command)
        val room = roomService.participateUnicast(command.sender, command.receiver)
        simpMessagingTemplate.convertAndSend("/sub/chat/"+room.id, newChat);
        return newChat.id?.let { 1 } ?: run{0}
    }

    @GetMapping("/{roomId}/list")
    fun list(@PathVariable roomId: Long): List<Chat> {
        log.info("list {}",roomId)
        return chatService.list(roomId)
    }
}
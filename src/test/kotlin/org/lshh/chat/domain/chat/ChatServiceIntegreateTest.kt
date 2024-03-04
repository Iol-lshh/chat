package org.lshh.chat.domain.chat

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.lshh.chat.domain.chat.dto.ChatCommand
import org.lshh.chat.domain.room.RoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class ChatServiceIntegreateTest (@Autowired val chatService : ChatService, @Autowired val roomService: RoomService){

    @BeforeEach
    fun beforeEach(){
        roomService.dropAll()
    }
    @Test
    fun publish_success(){

    }
    @Test
    fun unicast_success(){

    }
    @Test
    fun subscribe_success(){
        val roomId = 1L
        val chatCommand = ChatCommand(1, 2, "test", roomId)
        roomService.participateUnicast(chatCommand.sender, chatCommand.receiver)

        // TODO
        // 구독하고, 보내고, 받기 확인
    }

}
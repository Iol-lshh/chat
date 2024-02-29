package org.lshh.chat.domain.chat

import org.junit.jupiter.api.Test
import org.lshh.chat.domain.chat.dto.ChatCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChatServiceIntegrationTest(
        @Autowired
        val chatService: ChatService
) {
    @Test
    fun save_success(){
        val stubCommand = ChatCommand(
                1, 2, "", 1
        )
        val stubChat = Chat.createUnicast(stubCommand)

        val result = chatService.unicast(stubCommand)
        assert(result.sender == stubChat.sender)
    }

    @Test
    fun readChats_success(){
        val stubCommand = ChatCommand(
                1, 2, "", 1
        )
        Chat.createUnicast(stubCommand)
        chatService.unicast(stubCommand)
        val result = chatService.list(stubCommand.sender)
        assert(!result.isEmpty())
    }
}
package org.lshh.chat.domain.chat

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChatServiceIntegrationTest {
    @Autowired
    lateinit var chatService: ChatService

    @Test
    fun save_success(){
        val stubCommand = ChatComand(
                1, 2, ""
        )
        val stubChat = Chat.create(stubCommand)

        val result = chatService.save(stubCommand)
        assert(result.sender == stubChat.sender)
    }

    @Test
    fun readChats_success(){
        val stubCommand = ChatComand(
                1, 2, ""
        )
        val stubChat = Chat.create(stubCommand)

        chatService.save(stubCommand)
        val result = chatService.readChats(stubCommand.sender)
        assert(!result.isEmpty())
    }
}
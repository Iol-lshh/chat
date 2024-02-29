package org.lshh.chat.domain.chat

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
class ChatServiceTest{
    @Mock
    lateinit var chatRepository: ChatRepository

    @InjectMocks
    lateinit var chatService: ChatService

    @Test
    fun save_success(){
        val stubCommand = ChatComand(
                1, 2, ""
        )
        val stubChat = Chat.create(stubCommand)
        given(chatRepository.save(any())).willReturn(1)

        val result = chatService.save(stubCommand)
        assert(result.sender == stubChat.sender)
    }
    @Test
    fun readChats_success(){
        val stubCommand = ChatComand(
                1, 2, ""
        )
        val stubChat = Chat.create(stubCommand)
        val stubList = listOf(stubChat)
        given(chatRepository.findByUserId(any())).willReturn(stubList)

        val result = chatService.readChats(stubCommand.sender)
        assert(!result.isEmpty())
    }
}
package org.lshh.chat.domain.chat

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.lshh.chat.domain.chat.dto.ChatCommand
import org.lshh.chat.domain.room.Room
import org.lshh.chat.domain.room.RoomRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
class ChatServiceTest{
    @Mock
    lateinit var chatRepository: ChatRepository
    @Mock
    lateinit var roomRepository: RoomRepository
    @InjectMocks
    lateinit var chatService: ChatService

    @Test
    fun save_success(){
        val stubCommand = ChatCommand(
                1, 2, "", 1
        )
        val stubChat = Chat.createUnicast(stubCommand)
        val stubRoom = Room.create(stubCommand.sender,stubCommand.receiver)
        stubRoom.id=1

        given(roomRepository.findUnicastByUsers(1,2)).willReturn(stubRoom)
        given(chatRepository.save(any())).willReturn(1)

        val result = chatService.unicast(stubCommand)
        assert(result.sender == stubChat.sender)
    }
    @Test
    fun readChats_success(){
        val stubCommand = ChatCommand(
                1, 2, "", 1
        )
        val stubChat = Chat.createUnicast(stubCommand)
        val stubList = listOf(stubChat)
        given(chatRepository.findByRoomId(any())).willReturn(stubList)

        val result = chatService.list(stubCommand.sender)
        assert(!result.isEmpty())
    }
}
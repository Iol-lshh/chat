package org.lshh.chat.domain.chat

import org.lshh.chat.domain.chat.dto.ChatCommand
import org.lshh.chat.domain.room.RoomRepository
import org.springframework.stereotype.Service

@Service
class ChatService(
        private val repository: ChatRepository,
        private val roomRepository: RoomRepository
) {
    fun unicast(command: ChatCommand): Chat {
        val room = roomRepository.findUnicastByUsers(command.sender, command.receiver)

        if(room?.id != command.room){
            throw RuntimeException("잘못된 전송")
        }

        val newChat = Chat.createUnicast(command)
        val resultCnt = repository.save(newChat)
        
        if(resultCnt == 0){
            throw RuntimeException("저장 불가")
        }

        return newChat
    }

    fun list(roomId: Long): List<Chat> {
        return repository.findByRoomId(roomId)
    }
}
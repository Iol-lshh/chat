package org.lshh.chat.domain.chat

import org.lshh.chat.domain.chat.dto.ChatCommand
import org.lshh.chat.domain.room.RoomRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ChatService(
        private val repository: ChatRepository,
        private val roomRepository: RoomRepository,
        private val chatPubSub: ChatPubSub
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
        publish(newChat)
        return newChat
    }

    fun list(roomId: Long): List<Chat> {
        return repository.findByRoomId(roomId)
    }

    fun publish(chat: Chat){
        chatPubSub.publish(chat)
    }

    fun subscribe(roomId: Long): Flux<Chat> {
        return chatPubSub.subscribe(roomId)
    }
}
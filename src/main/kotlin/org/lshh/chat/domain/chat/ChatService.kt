package org.lshh.chat.domain.chat

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ChatService(val repository: ChatRepository) {
    fun save(chat: ChatComand): Chat {
        val newChat = Chat(
                sender = chat.sender,
                receiver = chat.receiver,
                contents = chat.contents,
                registed = LocalDateTime.now(),
                notReadedCnt = 1
        )
        repository.save(newChat)
        return newChat
    }

    fun readChats(userId: Long): List<Chat> {
        return repository.findByUserId(userId)
    }
}
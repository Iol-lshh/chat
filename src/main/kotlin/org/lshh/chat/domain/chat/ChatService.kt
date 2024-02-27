package org.lshh.chat.domain.chat

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ChatService(val repository: ChatRepository) {
    fun send(chat: ChatComand): Int {
        val newChat = Chat(
                sender = chat.sender,
                receiver = chat.receiver,
                contents = chat.contents,
                registed = LocalDateTime.now(),
                notReadedCnt = 1
        )
        return repository.save(newChat)
    }

    fun readChats(userId: Long): List<Chat> {
        return repository.findByUserId(userId)
    }
}
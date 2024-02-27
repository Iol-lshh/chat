package org.lshh.chat.domain.chat

import org.springframework.stereotype.Service

@Service
class ChatService(val repository: ChatRepository) {
    fun send(chat: Chat): Int {
        return repository.save(chat)
    }

    fun readChats(userId: Long): List<Chat> {
        return repository.findByUserId(userId)
    }
}
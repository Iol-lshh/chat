package org.lshh.chat.domain.chat

import org.springframework.stereotype.Service

@Service
class ChatService(val repository: ChatRepository) {
    fun save(commnad: ChatComand): Chat {
        val newChat = Chat.create(commnad)
        val resultCnt = repository.save(newChat)

        if(resultCnt == 0){
            throw RuntimeException("저장 불가")
        }

        return newChat
    }

    fun readChats(userId: Long): List<Chat> {
        return repository.findByUserId(userId)
    }
}
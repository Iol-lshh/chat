package org.lshh.chat.infra.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.ChatRepository
import org.lshh.chat.infra.Database
import org.springframework.stereotype.Repository

@Repository
class ChatRepositoryImplement(val database: Database): ChatRepository {
    override fun save(chat: Chat): Int {
        return database.saveChat(chat)
    }

    override fun findByUserId(userId: Long): List<Chat> {
        return database.listChat(userId)
    }
}
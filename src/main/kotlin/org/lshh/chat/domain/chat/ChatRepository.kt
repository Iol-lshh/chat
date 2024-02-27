package org.lshh.chat.domain.chat

interface ChatRepository {
    fun save(chat: Chat): Int
    fun findByUserId(userId: Long): List<Chat>
}
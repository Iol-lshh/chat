package org.lshh.chat.domain.chat

interface ChatRepository {
    fun save(chat: Chat): Int
    fun findByRoomId(roomId: Long): List<Chat>
}
package org.lshh.chat.domain.chat

import reactor.core.publisher.Flux

interface ChatRepository {
    fun save(chat: Chat): Int
    fun findByRoomId(roomId: Long): List<Chat>
}
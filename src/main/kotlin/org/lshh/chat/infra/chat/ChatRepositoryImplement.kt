package org.lshh.chat.infra.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.ChatRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
class ChatRepositoryImplement(val database: ChatDatabase): ChatRepository {
    override fun save(chat: Chat): Int {
        return database.save(chat)
    }

    override fun findByRoomId(roomId: Long): List<Chat> {
        return database.findByRoom(roomId)
    }
}
package org.lshh.chat.infra.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.infra.Database
import org.springframework.stereotype.Component

@Component
class ChatDatabase : Database<Chat>() {

    fun save(chat: Chat): Int {
        Thread.sleep(Math.random().toLong() * 300L + 100)
        val sequence = maxId() + 1
        chat.id = sequence
        db[sequence] = chat
        return 1
    }

    fun findByRoom(roomId: Long): List<Chat> {
        Thread.sleep(Math.random().toLong() * 100L + 100)
        return db.values.filter { it.room == roomId }
    }

    private fun maxId(): Long {
        sequence.lock()
        try{
            return db.keys.maxOrNull() ?: 0
        }finally {
            sequence.unlock()
        }
    }
}
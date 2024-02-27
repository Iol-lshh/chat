package org.lshh.chat.infra

import org.lshh.chat.domain.chat.Chat
import org.springframework.stereotype.Component

@Component
class Database {
    private val db: HashMap<Long, Chat> = HashMap()

    fun saveChat(chat: Chat): Int {
        Thread.sleep(Math.random().toLong() * 300L + 100)
        db[chat.id] = chat
        return 1
    }

    fun listChat(user: Long): List<Chat> {
        Thread.sleep(Math.random().toLong() * 100L + 100)
        return db.values.filter { it.receiver == user || it.sender == user }
    }
}
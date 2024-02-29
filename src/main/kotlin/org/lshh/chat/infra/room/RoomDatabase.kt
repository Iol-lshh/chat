package org.lshh.chat.infra.room

import org.lshh.chat.domain.room.Room
import org.lshh.chat.infra.Database
import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock

@Component
class RoomDatabase: Database<Room>() {
    private val xlock = ReentrantLock()

    fun save(room: Room): Room {
        Thread.sleep(Math.random().toLong() * 300L + 100)
        xlock.lock()
        try{
            if(room.id == null){
                val sequence = maxId() + 1
                room.id = sequence
            }
            db[room.id!!] = room
        }finally {
            xlock.unlock()
        }
        return room
    }

    fun findByUser(user: Long): List<Room> {
        Thread.sleep(Math.random().toLong() * 100L + 100)
        return db.values.filter { it.participants.contains(user) }
    }

    fun findUnicastByUsers(user1: Long, user2: Long): Room?{
        Thread.sleep(Math.random().toLong() * 100L + 100)
        return db.values.firstOrNull { it.participants.size == 2 && it.participants.contains(user1) && it.participants.contains(user2)}
    }

    override fun find(id: Long): Room? {
        Thread.sleep(Math.random().toLong() * 100L + 100)
        return db[id]
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
package org.lshh.chat.infra.room

import org.lshh.chat.domain.room.Room
import org.lshh.chat.domain.room.RoomRepository
import org.springframework.stereotype.Repository

@Repository
class RoomRepositoryImplement(val database: RoomDatabase): RoomRepository {
    override fun findByUser(user: Long):List<Room>{
        return database.findByUser(user)
    }
    override fun findUnicastByUsers(user1: Long, user2: Long): Room? {
        return database.findUnicastByUsers(user1, user2)
    }
    override fun find(room: Long):Room?{
        return database.find(room)
    }
    override fun save(room: Room):Room{
        return database.save(room)
    }

    override fun findAll(): List<Room> {
        return database.findAll().toList()
    }

    override fun drop(id: Long) {
        database.remove(id)
    }

    override fun dropAll() {
        database.removeAll()
    }
}
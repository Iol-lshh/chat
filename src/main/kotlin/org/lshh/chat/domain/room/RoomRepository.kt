package org.lshh.chat.domain.room

interface RoomRepository {
    fun findByUser(user: Long): List<Room>
    fun findUnicastByUsers(user1:Long, user2:Long): Room?
    fun find(room: Long): Room?
    fun save(room: Room): Room
    fun findAll(): List<Room>
    fun drop(id: Long)
    fun dropAll()
}
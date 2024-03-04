package org.lshh.chat.domain.room

class Room (
        var id: Long?,
        val participants: List<Long>
){
    companion object{
        fun create(id: Long): Room{
            return Room(id = id, participants = listOf())
        }
        fun create(vararg participants: Long): Room{
            return Room(id = null, participants = participants.toList())
        }
    }
}

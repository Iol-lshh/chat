package org.lshh.chat.domain.room

import org.springframework.stereotype.Service

@Service
class RoomService (private val repository: RoomRepository){
    fun participateUnicast(user1:Long, user2:Long): Room {
        var room = repository.findUnicastByUsers(user1, user2)
        if(room == null){
            room = Room.create(user1, user2)
            room = repository.save(room)
        }
        return room
    }

    fun all():List<Room>{
        return repository.findAll()
    }

}
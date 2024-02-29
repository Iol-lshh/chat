package org.lshh.chat.controller

import org.lshh.chat.domain.room.Room
import org.lshh.chat.domain.room.RoomService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/room")
class RoomController (
        private val service: RoomService
){
    @GetMapping("/unicast/{sender}/{receiver}")
    fun participateUnicast(@PathVariable sender: Long, @PathVariable receiver: Long):Room{
        return service.participateUnicast(sender, receiver)
    }

    @GetMapping("/all")
    fun all():List<Room>{
        return service.all()
    }
}

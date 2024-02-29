package org.lshh.chat.domain.chat

import org.lshh.chat.domain.chat.dto.ChatCommand
import java.time.LocalDateTime

class Chat(
        var id: Long?,
        val sender: Long,
        val receiver: Long,
        val room: Long,
        val contents: String,
        val registed: LocalDateTime = LocalDateTime.now(),
        val notReadedCnt: Long
){
    companion object {
        fun createUnicast(command: ChatCommand): Chat{
            return Chat(
                    id = null,
                    sender = command.sender,
                    receiver = command.receiver,
                    room = command.room,
                    contents = command.contents,
                    notReadedCnt = 1
            )
        }
    }
}
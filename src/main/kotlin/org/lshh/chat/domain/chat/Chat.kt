package org.lshh.chat.domain.chat

import java.time.LocalDateTime

class Chat(
        val sender: Long, val receiver: Long,
        val contents: String,
        val registed: LocalDateTime,
        val notReadedCnt: Long
){
    companion object {
        fun create(command: ChatComand): Chat{
            return Chat(
                    sender = command.sender,
                    receiver = command.receiver,
                    contents = command.contents,
                    registed = LocalDateTime.now(),
                    notReadedCnt = 1
            )
        }
    }
}
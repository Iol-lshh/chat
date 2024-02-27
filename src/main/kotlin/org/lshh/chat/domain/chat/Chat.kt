package org.lshh.chat.domain.chat

import java.time.LocalDateTime

data class Chat(
        val id: Long,
        val sender: Long, val receiver: Long,
        val contents: String,
        val registed: LocalDateTime,
        val notReadedCnt: Long
)

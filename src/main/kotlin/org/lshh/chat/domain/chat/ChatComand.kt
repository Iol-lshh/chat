package org.lshh.chat.domain.chat

data class ChatComand(
        val sender: Long, val receiver: Long, val contents: String
)

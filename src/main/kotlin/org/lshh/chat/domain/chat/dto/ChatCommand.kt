package org.lshh.chat.domain.chat.dto

data class ChatCommand(
    val sender: Long, val receiver: Long, val contents: String, val room: Long
)

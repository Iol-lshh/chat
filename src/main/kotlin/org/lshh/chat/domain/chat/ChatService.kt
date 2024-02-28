package org.lshh.chat.domain.chat

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class ChatService(val repository: ChatRepository) {
    fun save(chat: ChatComand): Mono<Chat> {
        val newChat = Chat(
                sender = chat.sender,
                receiver = chat.receiver,
                contents = chat.contents,
                registed = LocalDateTime.now(),
                notReadedCnt = 1
        )
        return repository.save(newChat).map { newChat }
    }

    fun readChats(userId: Long): Flux<List<Chat>> {
        return repository.findByUserId(userId)
    }
}
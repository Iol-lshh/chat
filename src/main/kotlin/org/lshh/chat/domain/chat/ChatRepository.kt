package org.lshh.chat.domain.chat

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ChatRepository {
    fun save(chat: Chat): Mono<Int>
    fun findByUserId(userId: Long): Flux<List<Chat>>
}
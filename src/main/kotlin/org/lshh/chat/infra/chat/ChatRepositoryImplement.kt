package org.lshh.chat.infra.chat

import org.lshh.chat.domain.chat.Chat
import org.lshh.chat.domain.chat.ChatRepository
import org.lshh.chat.infra.Database
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class ChatRepositoryImplement(val database: Database): ChatRepository {
    override fun save(chat: Chat): Mono<Int> {
        return Mono.just(database.saveChat(chat))
    }

    override fun findByUserId(userId: Long): Flux<List<Chat>> {
        return Flux.just(database.listChat(userId))
    }
}
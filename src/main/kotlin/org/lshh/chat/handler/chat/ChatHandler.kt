package org.lshh.chat.handler.chat

import com.fasterxml.jackson.databind.ObjectMapper
import org.lshh.chat.domain.chat.ChatComand
import org.lshh.chat.domain.chat.ChatService
import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class ChatHandler(val chatService: ChatService, val simpMessagingTemplate: SimpMessagingTemplate) : WebSocketHandler {
    private val log = LoggerFactory.getLogger(this.javaClass)!!
    private val objectMapper = ObjectMapper()

    override fun handle(session: WebSocketSession): Mono<Void> {
        return session.receive() // 데이터를 수신하고
                .map { it.payloadAsText } // 페이로드를 텍스트로 변환한 후
                .map { objectMapper.readValue(it, ChatComand::class.java) } // 그 텍스트를 ChatMessage 객체로 변환
                .map { handleMessage(it) } // 변환된 메시지를 처리
                .map { session.textMessage(it) } // Response 메시지를 생성하고
                .let { session.send(it) } // 생성된 메시지를 클라이언트로 다시 보냅니다.
    }

    private fun handleMessage(chat: ChatComand): String {
        log.info("Received chat contents: {}", chat.contents)
        val newChat = chatService.save(chat)

        // newChat이 null일 경우에 대한 예외 처리 코드를 추가하는 것이 좋습니다.
        simpMessagingTemplate.convertAndSend("/sub/chat", newChat)

        // JSON으로 변환된 Chat 메시지를 반환합니다.
        return objectMapper.writeValueAsString(newChat)
    }
}


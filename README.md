# 개요
- [웹소켓 방식 채팅](https://github.com/Iol-lshh/chat/tree/feature/spring-web)
- [SSE 방식 채팅](https://github.com/Iol-lshh/chat/tree/feature/spring-webflux)
- [프런트](./docs/test-chat.html)
    - 브랜치마다 프런트가 변경된다.

# 개념
## HTTP vs WebSocket vs SSE
### HTTP
- 하나의 request / 하나의 response
### WebSocket
- 요청 => 양방향 연결 스트림 유지 (stateful 통신)
### SSE (Server Sent Events)
- 하나의 request / response 스트림

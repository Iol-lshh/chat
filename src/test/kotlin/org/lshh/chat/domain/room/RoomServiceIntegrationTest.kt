package org.lshh.chat.domain.room

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RoomServiceIntegrationTest(
    @Autowired
    val roomService: RoomService
) {

}
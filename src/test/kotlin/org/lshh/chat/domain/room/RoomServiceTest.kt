package org.lshh.chat.domain.room

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RoomServiceTest{
    @Mock
    lateinit var repository: RoomRepository
    @InjectMocks
    lateinit var service: RoomService

    @Test
    fun participateUnicastTest() {

    }
}
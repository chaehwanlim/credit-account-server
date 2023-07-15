package chlim.creditaccount.domain.store.usecase

import chlim.creditaccount.domain.store.exception.StoreAlreadyExistsException
import chlim.creditaccount.domain.store.repository.StoreRepository
import chlim.creditaccount.domain.store.usecase.command.CreateStoreCommand
import chlim.creditaccount.domain.user.exception.UserNotFoundException
import chlim.creditaccount.domain.user.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.mockk.every
import io.mockk.mockk
import java.util.*

class CreateStoreTest: FreeSpec({

    val userRepository: UserRepository = mockk()
    val storeRepository: StoreRepository = mockk()
    val createStore = CreateStore(userRepository, storeRepository)

    val command = CreateStoreCommand(
        userId = 1L,
        name = "가게",
        phoneNumber = "01012345678"
    )

    "유저가 없으면" - {
        every { userRepository.findById(any()) } returns Optional.empty()

        "가게를 생성할 수 없다." {
            shouldThrow<UserNotFoundException> {
                createStore.execute(command)
            }
        }
    }

    "유저가 존재하고" - {
        every { userRepository.findById(any()) } returns Optional.of(mockk(
            relaxed = true
        ))

        "동일한 이름의 가게가 존재하면" - {
            every { storeRepository.findByUserAndName(any(), any()) } returns mockk(
                relaxed = true
            )

            "가게를 생성할 수 없다." {
                shouldThrow<StoreAlreadyExistsException> {
                    createStore.execute(command)
                }
            }
        }

        "동일한 이름의 가게가 존재하지 않으면" - {
            every { storeRepository.findByUserAndName(any(), any()) } returns null
            every { storeRepository.save(any()) } returns mockk(
                relaxed = true
            )

            "가게를 생성할 수 있다." {
                createStore.execute(command)
            }
        }
    }
})

package chlim.creditaccount.domain.user.usecase

import chlim.creditaccount.domain.user.exception.UserAlreadyExistsException
import chlim.creditaccount.domain.user.repository.UserRepository
import chlim.creditaccount.domain.user.usecase.command.CreateUserCommand
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.mockk.every
import io.mockk.mockk

internal class CreateUserTest : FreeSpec({

    val userRepository: UserRepository = mockk()
    val createUser = CreateUser(userRepository)

    val command = CreateUserCommand(
        name = "홍길동",
        email = "chlim428@gmail.com",
        phoneNumber = "01012345678"
    )

    "이미 동일한 이메일로 가입된 유저가 있으면" - {
        every { userRepository.findByEmail(any()) } returns mockk(relaxed = true)

        "유저를 생성할 수 없다." {
            shouldThrow<UserAlreadyExistsException> {
                createUser.execute(command)
            }
        }
    }

    "동일한 이메일로 가입된 유저가 없으면" - {
        every { userRepository.findByEmail(any()) } returns null
        every { userRepository.save(any()) } returns mockk(relaxed = true)

        "유저를 생성할 수 있다." {
            createUser.execute(command)
        }
    }
})

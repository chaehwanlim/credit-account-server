package chlim.creditaccount.domain.store.usecase.command

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import javax.validation.ConstraintViolationException

class CreateStoreCommandTest: FreeSpec({

    val validName = "가게"
    val validPhoneNumber = "01012345678"
    val validUserId = 1L

    "이름이 유효하지 않으면 가게를 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateStoreCommand(
            name = "  ",
            phoneNumber = validPhoneNumber,
            userId = validUserId
        )}
    }

    "전화번호가 유효하지 않으면 가게를 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateStoreCommand(
            name = validName,
            phoneNumber = "  ",
            userId = validUserId
        )}
    }

    "유저 아이디가 유효하지 않으면 가게를 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateStoreCommand(
            name = validName,
            phoneNumber = validPhoneNumber,
            userId = -1L
        )}
    }
})

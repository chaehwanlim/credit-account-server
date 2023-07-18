package chlim.creditaccount.domain.contact.usecase.command

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import javax.validation.ConstraintViolationException

class CreateContactCommandTest: FreeSpec({

    val validName = "홍길동"
    val validPhoneNumber = "01012345678"
    val validStoreId = 1L

    "이름이 유효하지 않으면 연락처를 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateContactCommand(
            name = "  ",
            phoneNumber = validPhoneNumber,
            memo = null,
            storeId = validStoreId
        )}
    }

    "전화번호가 유효하지 않으면 연락처를 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateContactCommand(
            name = validName,
            phoneNumber = "  ",
            memo = null,
            storeId = validStoreId
        )}
    }

    "가게 아이디가 유효하지 않으면 연락처를 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateContactCommand(
            name = validName,
            phoneNumber = validPhoneNumber,
            memo = null,
            storeId = -2L
        )}
    }
})

package chlim.creditaccount.domain.user.usecase.command

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import javax.validation.ConstraintViolationException

class CreateUserCommandTest: FreeSpec({
    val validName = "홍길동"
    val validEmail = "chlim428@gmail.com"
    val validPhoneNumber = "01012345678"

    "이름이 유효하지 않으면 유저를 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateUserCommand(
            name = "  ",
            email = validEmail,
            phoneNumber = validPhoneNumber
        )}
    }

    "이메일이 유효하지 않으면 유저를 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateUserCommand(
            name = validName,
            email = "  ",
            phoneNumber = validPhoneNumber
        )}
    }

    "전화번호가 유효하지 않으면 유저를 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateUserCommand(
            name = validName,
            email = validEmail,
            phoneNumber = "  "
        )}
    }
})

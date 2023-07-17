package chlim.creditaccount.domain.product.usecase.command

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import javax.validation.ConstraintViolationException

class CreateProductCommandTest: FreeSpec({
    
    val validName = "상품"
    val validPrice = 1000
    val validQuantity = 10
    val validStoreId = 1L

    "이름이 유효하지 않으면 상품을 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateProductCommand(
            name = "  ",
            price = validPrice,
            quantity = validQuantity,
            storeId = validStoreId
        )}
    }

    "가격이 유효하지 않으면 상품을 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateProductCommand(
            name = validName,
            price = -1,
            quantity = validQuantity,
            storeId = validStoreId
        )}
    }

    "수량이 유효하지 않으면 상품을 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateProductCommand(
            name = validName,
            price = validPrice,
            quantity = -1,
            storeId = validStoreId
        )}
    }

    "가게 아이디가 유효하지 않으면 상품을 생성할 수 없다." {
        shouldThrow<ConstraintViolationException> { CreateProductCommand(
            name = validName,
            price = validPrice,
            quantity = validQuantity,
            storeId = -2L
        )}
    }
})
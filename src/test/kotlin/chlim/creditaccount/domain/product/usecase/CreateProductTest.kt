package chlim.creditaccount.domain.product.usecase

import chlim.creditaccount.domain.product.repository.ProductRepository
import chlim.creditaccount.domain.product.usecase.command.CreateProductCommand
import chlim.creditaccount.domain.store.exception.StoreNotFoundException
import chlim.creditaccount.domain.store.repository.StoreRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.mockk.every
import io.mockk.mockk
import java.util.*

class CreateProductTest: FreeSpec({

    val storeRepository: StoreRepository = mockk()
    val productRepository: ProductRepository = mockk()
    val createProduct = CreateProduct(storeRepository, productRepository)

    val command = CreateProductCommand(
        name = "상품",
        price = 1000,
        quantity = 10,
        storeId = 1L
    )

    "가게가 없으면" - {
        every { storeRepository.findById(any()) } returns Optional.empty()

        "상품을 생성할 수 없다." {
            shouldThrow<StoreNotFoundException> {
                createProduct.execute(command)
            }
        }
    }

    "가게가 존재하고" - {
        every { storeRepository.findById(any()) } returns Optional.of(mockk(
            relaxed = true
        ))

        "동일한 이름의 상품이 존재하면" - {
            every { productRepository.findByStoreAndName(any(), any()) } returns mockk(
                relaxed = true
            )

            "상품을 생성할 수 없다." {
                shouldThrow<StoreNotFoundException> {
                    createProduct.execute(command)
                }
            }
        }

        "동일한 이름의 상품이 존재하지 않으면" - {
            every { productRepository.findByStoreAndName(any(), any()) } returns null
            every { productRepository.save(any()) } returns mockk(
                relaxed = true
            )

            "상품을 생성할 수 있다." {
                createProduct.execute(command)
            }
        }
    }
})

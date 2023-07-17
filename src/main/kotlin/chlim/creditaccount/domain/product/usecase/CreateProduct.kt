package chlim.creditaccount.domain.product.usecase

import chlim.creditaccount.domain.product.Product
import chlim.creditaccount.domain.product.repository.ProductRepository
import chlim.creditaccount.domain.product.usecase.command.CreateProductCommand
import chlim.creditaccount.domain.product.usecase.result.ProductResult
import chlim.creditaccount.domain.store.exception.StoreNotFoundException
import chlim.creditaccount.domain.store.repository.StoreRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CreateProduct(
    private val storeRepository: StoreRepository,
    private val productRepository: ProductRepository
) {

    @Transactional
    fun execute(command: CreateProductCommand): ProductResult {
        val store = storeRepository.findById(command.storeId)
            .orElseThrow { StoreNotFoundException(command.storeId) }

        if (productRepository.findByStoreAndName(store, command.name) != null) {
            throw StoreNotFoundException(command.storeId)
        }

        val product = run {
            val product = Product(
                name = command.name,
                price = command.price,
                quantity = command.quantity,
                store = store
            )

            productRepository.save(product)
        }

        return ProductResult.from(product)
    }
}
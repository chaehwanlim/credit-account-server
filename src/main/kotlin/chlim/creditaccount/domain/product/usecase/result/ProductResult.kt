package chlim.creditaccount.domain.product.usecase.result

import chlim.creditaccount.domain.product.Product
import chlim.creditaccount.domain.store.usecase.result.StoreResult

data class ProductResult(
    val name: String,
    val price: Int,
    val quantity: Int,
    val store: StoreResult
) {

    companion object {

        fun from(product: Product) = ProductResult(
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            store = StoreResult.from(product.store)
        )
    }
}

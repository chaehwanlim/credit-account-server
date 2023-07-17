package chlim.creditaccount.domain.product.repository

import chlim.creditaccount.domain.product.Product
import chlim.creditaccount.domain.store.Store
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {

    fun findByStoreAndName(store: Store, name: String): Product?
}

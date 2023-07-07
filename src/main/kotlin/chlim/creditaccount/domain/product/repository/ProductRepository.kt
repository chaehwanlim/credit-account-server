package chlim.creditaccount.domain.product.repository

import chlim.creditaccount.domain.product.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {}

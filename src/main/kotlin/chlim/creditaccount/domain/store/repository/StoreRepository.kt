package chlim.creditaccount.domain.store.repository

import chlim.creditaccount.domain.store.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository: JpaRepository<Store, Long> {}

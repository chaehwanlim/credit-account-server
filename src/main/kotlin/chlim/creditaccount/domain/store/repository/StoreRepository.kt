package chlim.creditaccount.domain.store.repository

import chlim.creditaccount.domain.store.Store
import chlim.creditaccount.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository: JpaRepository<Store, Long> {

    fun findByUserAndName(user: User, name: String): Store?
}

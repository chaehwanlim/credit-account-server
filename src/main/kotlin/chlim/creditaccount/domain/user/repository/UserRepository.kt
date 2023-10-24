package chlim.creditaccount.domain.user.repository

import chlim.creditaccount.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun existsByEmailOrPhoneNumber(email: String, phoneNumber: String): Boolean
}

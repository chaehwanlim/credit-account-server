package chlim.creditaccount.domain.authmethod.repository

import chlim.creditaccount.domain.authmethod.AuthMethod
import chlim.creditaccount.domain.authmethod.AuthMethodType
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface AuthMethodRepository: JpaRepository<AuthMethod, Long> {

    @EntityGraph(attributePaths = ["user"])
    fun findByResourceIdAndType(resourceId: String, type: AuthMethodType): AuthMethod?
}

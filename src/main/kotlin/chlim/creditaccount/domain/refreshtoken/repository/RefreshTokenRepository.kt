package chlim.creditaccount.domain.refreshtoken.repository

import chlim.creditaccount.domain.refreshtoken.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository: JpaRepository<RefreshToken, Long> {

    fun findByTokenAndExpiresAtNullOrRevokedAtNull(token: String): RefreshToken?
}

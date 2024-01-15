package chlim.creditaccount.domain.refreshtoken

import chlim.creditaccount.common.AbstractEntity
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "refresh_tokens")
class RefreshToken(
    token: String
): AbstractEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var token: String = token

    var expiresAt: ZonedDateTime? = null

    var revokedAt: ZonedDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (this.javaClass != other?.javaClass) {
            return false
        }

        other as RefreshToken

        return this.token == other.token
    }

    override fun hashCode(): Int {
        return this.token.hashCode()
    }

    override fun toString(): String {
        return "RefreshToken=(id=${this.id}, token='${this.token})"
    }

}
package chlim.creditaccount.domain.authmethod

import chlim.creditaccount.common.AbstractEntity
import chlim.creditaccount.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "auth_methods")
class AuthMethod(
    user: User,
    type: AuthMethodType,
    resourceId: String?
): AbstractEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User = user

    @Enumerated(EnumType.STRING)
    val type: AuthMethodType = type

    val resourceId: String? = resourceId

    init {
        user.addAuthMethod(this)
    }
}

enum class AuthMethodType {
    PASSWORD, KAKAO
}

package chlim.creditaccount.domain.user

import chlim.creditaccount.common.AbstractEntity
import chlim.creditaccount.domain.authmethod.AuthMethod
import chlim.creditaccount.domain.shared.PhoneNumber
import chlim.creditaccount.domain.store.Store
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    name: String,
    email: String,
    phoneNumber: String?
) : AbstractEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val name: String = name

    val email: String = email

    val phoneNumber: PhoneNumber? = if (phoneNumber != null) PhoneNumber(phoneNumber) else null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val stores: MutableList<Store> = mutableListOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val authMethods: List<AuthMethod> = mutableListOf()

    fun addAuthMethod(authMethod: AuthMethod) {
        (this.authMethods as MutableList).add(authMethod)
    }

    fun getRoles(): List<String> {
        return mutableListOf("ROLE_USER")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (this.javaClass != other?.javaClass) {
            return false
        }

        other as User

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "User=(id=${this.id}, name='${this.name}', email='${this.email}', phone='${this.phoneNumber})"
    }
}

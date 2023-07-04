package chlim.creditaccount.domain.user

import chlim.creditaccount.common.AbstractEntity
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    name: String,
    email: String,
    phoneNumber: String
): AbstractEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    var email: String = email

    var phoneNumber: PhoneNumber = PhoneNumber(phoneNumber)

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

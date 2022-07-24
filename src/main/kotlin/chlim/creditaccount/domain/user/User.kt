package chlim.creditaccount.domain.user

import javax.persistence.*

@Entity
@Table(name = "user")
class User(name: String, email: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    var email: String = email

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (this.javaClass != other?.javaClass) {
            return false
        }

        other as User

        if (this.id != other.id) {
            return false
        }

        return true
    }

    override fun hashCode(): Int {
        return this.id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "User=(id=${this.id}, name='${this.name}', email='${this.email}')"
    }

}

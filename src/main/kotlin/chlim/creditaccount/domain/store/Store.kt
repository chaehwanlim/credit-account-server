package chlim.creditaccount.domain.store

import chlim.creditaccount.common.AbstractEntity
import chlim.creditaccount.domain.contact.Contact
import chlim.creditaccount.domain.product.Product
import chlim.creditaccount.domain.shared.PhoneNumber
import chlim.creditaccount.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "stores")
class Store(
    name: String,
    phoneNumber: String,
    user: User,
): AbstractEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    var phoneNumber: PhoneNumber = PhoneNumber(phoneNumber)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User = user

    @OneToMany(mappedBy = "store")
    var contacts: MutableList<Contact> = mutableListOf()

    @OneToMany(mappedBy = "store")
    var products: MutableList<Product> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (this.javaClass != other?.javaClass) {
            return false
        }

        other as Store

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Store=(id=${this.id}, name='${this.name}', phone='${this.phoneNumber})"
    }
}

package chlim.creditaccount.domain.contact

import chlim.creditaccount.common.AbstractEntity
import chlim.creditaccount.domain.shared.PhoneNumber
import chlim.creditaccount.domain.store.Store
import jakarta.persistence.*

@Entity
@Table(name = "contacts")
class Contact(
    name: String,
    phoneNumber: String,
    memo: String?,
    store: Store
): AbstractEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    var phoneNumber: PhoneNumber = PhoneNumber(phoneNumber)

    var memo: String? = memo

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    var store: Store = store

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (this.javaClass != other?.javaClass) {
            return false
        }

        other as Contact

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Contact=(id=${this.id}, name='${this.name}', phone='${this.phoneNumber}, memo='${this.memo})"
    }
}

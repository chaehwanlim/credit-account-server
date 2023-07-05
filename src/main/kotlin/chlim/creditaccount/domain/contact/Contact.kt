package chlim.creditaccount.domain.contact

import chlim.creditaccount.common.AbstractEntity
import chlim.creditaccount.domain.shared.PhoneNumber
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "contacts")
class Contact(
    name: String,
    phoneNumber: String,
    memo: String?
): AbstractEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    var phoneNumber: PhoneNumber = PhoneNumber(phoneNumber)

    var memo: String? = memo

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
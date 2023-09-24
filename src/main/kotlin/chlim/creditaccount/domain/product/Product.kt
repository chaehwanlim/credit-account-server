package chlim.creditaccount.domain.product

import chlim.creditaccount.common.AbstractEntity
import chlim.creditaccount.domain.store.Store
import jakarta.persistence.*

@Entity
@Table(name = "products")
class Product(
    name: String,
    price: Int,
    quantity: Int,
    store: Store
): AbstractEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    var price: Int = price

    var quantity: Int = quantity

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

        other as Product

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Product=(id=${this.id}, name='${this.name}', price='${this.price}, quantity='${this.quantity})"
    }
}

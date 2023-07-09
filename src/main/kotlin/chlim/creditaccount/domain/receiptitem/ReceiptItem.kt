package chlim.creditaccount.domain.receiptitem

import chlim.creditaccount.common.AbstractEntity
import chlim.creditaccount.domain.product.Product
import chlim.creditaccount.domain.receipt.Receipt
import javax.persistence.*

@Entity
@Table(name = "receipt_items")
class ReceiptItem(
    receipt: Receipt,
    price: Int,
    quantity: Int,
    product: Product
): AbstractEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "receipt_id", nullable = false)
    var receipt: Receipt = receipt

    var price: Int = price

    var quantity: Int = quantity

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product = product

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (this.javaClass != other?.javaClass) {
            return false
        }

        other as ReceiptItem

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "ReceiptItem=(id=${this.id}, price=${this.price}, quantity=${this.quantity}), productName=${this.product.name}"
    }
}

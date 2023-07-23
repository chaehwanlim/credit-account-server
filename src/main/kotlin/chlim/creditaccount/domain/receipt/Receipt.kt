package chlim.creditaccount.domain.receipt

import chlim.creditaccount.common.AbstractEntity
import chlim.creditaccount.domain.contact.Contact
import chlim.creditaccount.domain.product.Product
import chlim.creditaccount.domain.receiptitem.ReceiptItem
import chlim.creditaccount.domain.receiptitem.ReceiptItems
import chlim.creditaccount.domain.store.Store
import javax.persistence.*

@Entity
@Table(name = "receipts")
class Receipt(
    store: Store,
    contact: Contact,
    receiptItems: List<ReceiptItem>,
    memo: String?
): AbstractEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    var store: Store = store

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false)
    var contact: Contact = contact

    @Embedded
    private var receiptItems: ReceiptItems = ReceiptItems(receiptItems)

    val items
        get() = this.receiptItems.items()
    val totalPrice
        get() = this.receiptItems.totalPrice()

    var memo: String? = memo

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (this.javaClass != other?.javaClass) {
            return false
        }

        other as Receipt

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id?.hashCode() ?: 0
    }

}

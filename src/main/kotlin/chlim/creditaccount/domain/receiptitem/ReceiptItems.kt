package chlim.creditaccount.domain.receiptitem

import javax.persistence.CascadeType
import javax.persistence.Embeddable
import javax.persistence.OneToMany

@Embeddable
class ReceiptItems(
    @OneToMany(mappedBy = "receipt", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val items: List<ReceiptItem>
) {

    fun items(): List<ReceiptItem> = this.items

    fun totalPrice(): Int = this.items.sumOf { it.price * it.quantity }
}

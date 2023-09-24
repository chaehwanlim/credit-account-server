package chlim.creditaccount.domain.receiptitem

import jakarta.persistence.CascadeType
import jakarta.persistence.Embeddable
import jakarta.persistence.OneToMany

@Embeddable
class ReceiptItems(
    @OneToMany(mappedBy = "receipt", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val items: List<ReceiptItem>
) {

    fun items(): List<ReceiptItem> = this.items

    fun totalPrice(): Int = this.items.sumOf { it.price * it.quantity }
}

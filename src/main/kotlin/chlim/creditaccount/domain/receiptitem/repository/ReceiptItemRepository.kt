package chlim.creditaccount.domain.receiptitem.repository

import chlim.creditaccount.domain.receiptitem.ReceiptItem
import org.springframework.data.jpa.repository.JpaRepository

interface ReceiptItemRepository: JpaRepository<ReceiptItem, Long> {}

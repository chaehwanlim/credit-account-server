package chlim.creditaccount.domain.receipt.repository

import chlim.creditaccount.domain.receipt.Receipt
import org.springframework.data.jpa.repository.JpaRepository

interface ReceiptRepository: JpaRepository<Receipt, Long> {}

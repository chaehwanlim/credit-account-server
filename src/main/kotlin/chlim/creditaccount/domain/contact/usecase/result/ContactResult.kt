package chlim.creditaccount.domain.contact.usecase.result

import chlim.creditaccount.domain.contact.Contact
import chlim.creditaccount.domain.store.usecase.result.StoreResult

data class ContactResult(
    val name: String,
    val phoneNumber: String,
    val memo: String?,
    val store: StoreResult
) {

    companion object {

        fun from(contact: Contact) = ContactResult(
            name = contact.name,
            phoneNumber = contact.phoneNumber.value,
            memo = contact.memo,
            store = StoreResult.from(contact.store)
        )
    }
}

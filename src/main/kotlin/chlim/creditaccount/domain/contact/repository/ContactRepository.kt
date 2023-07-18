package chlim.creditaccount.domain.contact.repository

import chlim.creditaccount.domain.contact.Contact
import chlim.creditaccount.domain.store.Store
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository: JpaRepository<Contact, Long> {

    fun findByStoreAndPhoneNumber(store: Store, phoneNumber: String): Contact?
}

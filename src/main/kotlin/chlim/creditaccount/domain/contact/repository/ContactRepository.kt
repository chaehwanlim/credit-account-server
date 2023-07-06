package chlim.creditaccount.domain.contact.repository

import chlim.creditaccount.domain.contact.Contact
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository: JpaRepository<Contact, Long> {}

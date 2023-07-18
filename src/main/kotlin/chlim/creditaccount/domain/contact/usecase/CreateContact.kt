package chlim.creditaccount.domain.contact.usecase

import chlim.creditaccount.domain.contact.Contact
import chlim.creditaccount.domain.contact.exception.ContactAlreadyExistsException
import chlim.creditaccount.domain.contact.repository.ContactRepository
import chlim.creditaccount.domain.contact.usecase.command.CreateContactCommand
import chlim.creditaccount.domain.contact.usecase.result.ContactResult
import chlim.creditaccount.domain.store.exception.StoreNotFoundException
import chlim.creditaccount.domain.store.repository.StoreRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CreateContact(
    private val storeRepository: StoreRepository,
    private val contactRepository: ContactRepository
) {

    @Transactional
    fun execute(command: CreateContactCommand): ContactResult {
        val store = storeRepository.findById(command.storeId)
            .orElseThrow { StoreNotFoundException(command.storeId) }

        if (contactRepository.findByStoreAndPhoneNumber(store, command.phoneNumber) != null) {
            throw ContactAlreadyExistsException(store, command.phoneNumber)
        }

        val contact = run {
            val contact = Contact(
                name = command.name,
                phoneNumber = command.phoneNumber,
                memo = command.memo,
                store = store
            )

            contactRepository.save(contact)
        }

        return ContactResult.from(contact)
    }
}

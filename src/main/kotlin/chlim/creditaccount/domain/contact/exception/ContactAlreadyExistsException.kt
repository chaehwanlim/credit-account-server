package chlim.creditaccount.domain.contact.exception

import chlim.creditaccount.domain.store.Store

private const val MESSAGE = "Contact already exists."

class ContactAlreadyExistsException(
    store: Store,
    phoneNumber: String
): RuntimeException(
    MESSAGE + "storeName=${store.name}, phoneNumber=$phoneNumber"
)

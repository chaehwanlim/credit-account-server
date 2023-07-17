package chlim.creditaccount.domain.store.exception

private const val MESSAGE = "Store is not found."

class StoreNotFoundException(
    id: Long
): RuntimeException(
    MESSAGE + "id=$id"
)

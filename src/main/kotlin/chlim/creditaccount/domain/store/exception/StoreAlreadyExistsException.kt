package chlim.creditaccount.domain.store.exception

import chlim.creditaccount.domain.user.User

private const val MESSAGE = "Store already exists."

class StoreAlreadyExistsException(
    user: User,
    name: String
): RuntimeException(
    MESSAGE + "userName=$user.name, name=$name"
)

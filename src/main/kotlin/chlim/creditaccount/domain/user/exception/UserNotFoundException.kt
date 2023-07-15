package chlim.creditaccount.domain.user.exception

private const val MESSAGE = "User is not found."

class UserNotFoundException(
    id: Long
): RuntimeException(
    MESSAGE + "id=$id"
)

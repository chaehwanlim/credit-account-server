package chlim.creditaccount.domain.user.exception

private const val MESSAGE_PREFIX = "User already exists. "

class UserAlreadyExistsException(
        message: String
): RuntimeException(
    MESSAGE_PREFIX + message
)

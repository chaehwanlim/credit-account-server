package chlim.creditaccount.domain.authmethod.exception

private const val MESSAGE_PREFIX = "AuthMethod already exists. "

class AuthMethodAlreadyExistsException(
    message: String
): RuntimeException(MESSAGE_PREFIX + message)

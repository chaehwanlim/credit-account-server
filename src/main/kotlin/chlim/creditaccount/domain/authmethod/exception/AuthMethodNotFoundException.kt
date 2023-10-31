package chlim.creditaccount.domain.authmethod.exception

import chlim.creditaccount.domain.authmethod.AuthMethodType

private const val MESSAGE = "Authentication is not found. "

class AuthMethodNotFoundException(
    resourceId: String,
    type: AuthMethodType
): RuntimeException("$MESSAGE resourceId=$resourceId, type=$type")

package chlim.creditaccount.domain.refreshtoken.exception

private const val MESSAGE = "Refresh token is invalid."

class RefreshTokenInvalidException(): RuntimeException(MESSAGE)

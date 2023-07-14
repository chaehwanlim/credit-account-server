package chlim.creditaccount.domain.user.exception

private const val MESSAGE = "User already exists."

class UserAlreadyExistsException(
        phoneNumber: String
): RuntimeException(
    MESSAGE + "phoneNumber=$phoneNumber"
)

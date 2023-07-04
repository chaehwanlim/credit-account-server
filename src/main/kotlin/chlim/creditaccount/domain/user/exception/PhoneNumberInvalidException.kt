package chlim.creditaccount.domain.user.exception

private const val MESSAGE = "Phone number is invalid."

class PhoneNumberInvalidException(
        phoneNumber: String
): RuntimeException(
        MESSAGE + phoneNumber
)

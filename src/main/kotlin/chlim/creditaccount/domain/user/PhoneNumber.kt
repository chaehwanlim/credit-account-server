package chlim.creditaccount.domain.user

import chlim.creditaccount.domain.user.exception.PhoneNumberInvalidException
import com.google.i18n.phonenumbers.PhoneNumberUtil

private const val DEFAULT_LOCALE_CODE = "KR"

@JvmInline
value class PhoneNumber(val value: String) {

    init {
        validatePhoneNumber(value)
    }

    private fun validatePhoneNumber(phoneNumber: String) {
        val phoneUtil = PhoneNumberUtil.getInstance()
        try {
            val parsedPhoneNumber = phoneUtil.parse(phoneNumber, DEFAULT_LOCALE_CODE)

            if (!phoneUtil.isValidNumber(parsedPhoneNumber)) {
                throw PhoneNumberInvalidException(phoneNumber)
            }
        } catch (e: Exception) {
            throw PhoneNumberInvalidException(phoneNumber)
        }
    }

}
package chlim.creditaccount.domain.user.usecase.result

import chlim.creditaccount.domain.user.User

data class UserResult (
    val name: String,
    val email: String,
    val phoneNumber: String
) {

    companion object {

        fun from(user: User) = UserResult(
            name = user.name,
            email = user.email,
            phoneNumber = user.phoneNumber.value
        )
    }
}

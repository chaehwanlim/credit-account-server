package chlim.creditaccount.domain.store.usecase.result

import chlim.creditaccount.domain.shared.PhoneNumber
import chlim.creditaccount.domain.user.usecase.result.UserResult

data class StoreResult(
    val name: String,
    val phoneNumber: PhoneNumber,
    val user: UserResult
)

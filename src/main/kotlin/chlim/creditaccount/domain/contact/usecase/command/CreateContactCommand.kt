package chlim.creditaccount.domain.contact.usecase.command

import chlim.creditaccount.common.SelfValidator
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class CreateContactCommand(
    @field: NotBlank val name: String,
    @field: NotBlank val phoneNumber: String,
    val memo: String?,
    @field: Positive val storeId: Long
): SelfValidator<CreateContactCommand>() {

    init {
        validateAndIfViolatedThrows()
    }
}

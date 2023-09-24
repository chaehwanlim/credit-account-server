package chlim.creditaccount.domain.store.usecase.command

import chlim.creditaccount.common.SelfValidator
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class CreateStoreCommand(
    @field: NotBlank val name: String,
    @field: NotBlank val phoneNumber: String,
    @field: Positive val userId: Long
): SelfValidator<CreateStoreCommand>() {

    init {
        validateAndIfViolatedThrows()
    }
}

package chlim.creditaccount.domain.user.usecase.command

import chlim.creditaccount.common.SelfValidator
import javax.validation.constraints.NotBlank


data class CreateUserCommand(
    @field: NotBlank val name: String,
    @field: NotBlank val email: String,
    @field: NotBlank val phoneNumber: String
): SelfValidator<CreateUserCommand>() {

    init {
        validateAndIfViolatedThrows()
    }
}

package chlim.creditaccount.domain.user.usecase.command

import chlim.creditaccount.common.SelfValidator
import jakarta.validation.constraints.NotBlank
import lombok.ToString

@ToString
data class CreateUserCommand(
    @field: NotBlank val name: String,
    @field: NotBlank val email: String,
    val phoneNumber: String?
) : SelfValidator<CreateUserCommand>() {

    init {
        validateAndIfViolatedThrows()
    }
}

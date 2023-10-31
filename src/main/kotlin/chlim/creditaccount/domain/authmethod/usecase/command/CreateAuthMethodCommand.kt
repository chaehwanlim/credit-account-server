package chlim.creditaccount.domain.authmethod.usecase.command

import chlim.creditaccount.common.SelfValidator
import chlim.creditaccount.domain.authmethod.AuthMethodType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateAuthMethodCommand(
    @field: NotNull val userId: Long,
    @field: NotBlank val resourceId: String,
    @field: NotNull val type: AuthMethodType,
) : SelfValidator<CreateAuthMethodCommand>() {

    init {
        validateAndIfViolatedThrows()
    }
}

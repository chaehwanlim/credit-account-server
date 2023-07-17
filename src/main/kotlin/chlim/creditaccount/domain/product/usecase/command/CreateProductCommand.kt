package chlim.creditaccount.domain.product.usecase.command

import chlim.creditaccount.common.SelfValidator
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

data class CreateProductCommand(
    @field:NotBlank val name: String,
    @field:Positive val price: Int,
    @field:Positive val quantity: Int,
    @field:Positive val storeId: Long
): SelfValidator<CreateProductCommand>() {

    init {
        validateAndIfViolatedThrows()
    }
}

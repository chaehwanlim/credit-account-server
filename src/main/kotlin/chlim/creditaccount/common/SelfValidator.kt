package chlim.creditaccount.common

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator

abstract class SelfValidator<T> {

    companion object {
        val validator: Validator = Validation.buildDefaultValidatorFactory().validator
    }

    protected fun validateAndIfViolatedThrows() {
        val violations: Set<ConstraintViolation<T>> = validator.validate(this as T)

        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }

    protected fun validate(): Set<ConstraintViolation<T>> {
        return validator.validate(this as T)
    }
}

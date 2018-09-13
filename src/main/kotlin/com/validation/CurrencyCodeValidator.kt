package com.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CurrencyCodeValidator : ConstraintValidator<CurrencyCode, String> {

    override fun isValid(currencyCode: String?, context: ConstraintValidatorContext?): Boolean {
        return currencyCode.equals("PLN")
    }
}
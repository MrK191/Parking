package com.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CurrencyCodeValidator::class])
annotation class CurrencyCode(val message: String = "Currently the only supported currency is PLN",
                              val groups: Array<KClass<out Any>> = [],
                              val payload: Array<KClass<out Any>> = [])
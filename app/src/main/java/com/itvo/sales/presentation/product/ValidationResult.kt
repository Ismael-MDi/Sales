package com.itvo.sales.presentation.product


sealed class ValidationResult {
    object Succes: ValidationResult()
    data class Error(val message: String): ValidationResult()
}
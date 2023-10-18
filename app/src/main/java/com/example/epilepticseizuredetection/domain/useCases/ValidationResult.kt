package com.example.epilepticseizuredetection.domain.useCases

data class ValidationResult (

val successful: Boolean,
val errorMessage: String? = null



)
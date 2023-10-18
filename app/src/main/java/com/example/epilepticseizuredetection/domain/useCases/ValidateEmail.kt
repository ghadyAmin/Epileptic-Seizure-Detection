package com.example.epilepticseizuredetection.domain.useCases

import android.util.Patterns

class ValidateEmail {

    fun execute(email: String): ValidationResult{

        if(email.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "Email cannot be empty"
            )

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                successful = false,
                errorMessage = "Not a valid email"
            )

        }
        return ValidationResult(
            successful = true
        )


    }



}
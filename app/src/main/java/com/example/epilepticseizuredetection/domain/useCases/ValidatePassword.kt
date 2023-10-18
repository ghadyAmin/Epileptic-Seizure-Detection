package com.example.epilepticseizuredetection.domain.useCases

import android.util.Patterns

class ValidatePassword {

    fun execute(password: String): ValidationResult{
        val containsLetterAndDigits = password.any(){it.isDigit()} &&
        password.any{it.isLetter()}

        if(password.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "password cannot be empty"
            )

        }
        if(password.length < 8){
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs to be at least 6 characters long"
            )

        }
        if (!containsLetterAndDigits){
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs to contain at least one letter and digit"
            )
        }
        return ValidationResult(
            successful = true
        )


    }





}
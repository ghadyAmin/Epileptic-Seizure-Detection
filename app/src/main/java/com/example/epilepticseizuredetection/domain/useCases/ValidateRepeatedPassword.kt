package com.example.epilepticseizuredetection.domain.useCases

class ValidateRepeatedPassword {


    fun execute(password: String, repeatedPassword: String): ValidationResult{


        if(password != repeatedPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "passwords don't match"
            )

        }
       
        return ValidationResult(
            successful = true
        )


    }






}
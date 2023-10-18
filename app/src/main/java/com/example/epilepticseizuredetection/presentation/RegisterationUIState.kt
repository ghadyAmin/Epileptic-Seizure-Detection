package com.example.epilepticseizuredetection.presentation

data class RegistrationUIState (
    var firstName :String = "",
    var lastName :String = "",
    var email :String = "",
    var emailError: String? = null,
    var password :String = "",
    var passwordError: String? = null,
    var repeatedPassword: String = "",
    var repeatedPasswordError: String? = null,

)
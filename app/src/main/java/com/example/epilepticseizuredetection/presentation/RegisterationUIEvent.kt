package com.example.epilepticseizuredetection.presentation

sealed class RegisterationUIEvent{


    data class FirstNameChanged (val firstName:String) : RegisterationUIEvent()
    data class LastNameChanged (val lastName:String) : RegisterationUIEvent()
    data class EmailChanged (val email: String): RegisterationUIEvent()
    data class PasswordChanged (val password: String): RegisterationUIEvent()
    data class RepeatedPasswordChanged (val repeatedPassword: String): RegisterationUIEvent()
    object Submit: RegisterationUIEvent()

}

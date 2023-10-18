package com.example.epilepticseizuredetection.presentation.viewModels

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.epilepticseizuredetection.domain.useCases.ValidateEmail
import com.example.epilepticseizuredetection.domain.useCases.ValidatePassword
import com.example.epilepticseizuredetection.domain.useCases.ValidateRepeatedPassword
import com.example.epilepticseizuredetection.presentation.RegisterationUIEvent
import com.example.epilepticseizuredetection.presentation.RegistrationUIState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword()
): ViewModel()  {

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()


    var state by mutableStateOf(RegistrationUIState())


    fun onEvent(event : RegisterationUIEvent){
        when (event) {
            is RegisterationUIEvent.EmailChanged -> {
                state = state. copy (email = event.email)

            }
            is RegisterationUIEvent.PasswordChanged ->{
                state = state. copy (password = event.password)
            }
            is RegisterationUIEvent.RepeatedPasswordChanged ->{
                state = state. copy (repeatedPassword = event.repeatedPassword)
            }
            is RegisterationUIEvent.LastNameChanged -> {
                state = state. copy (lastName = event.lastName)
            }
            is RegisterationUIEvent.FirstNameChanged -> {
                state = state. copy (firstName = event.firstName)
            }
            is RegisterationUIEvent.Submit -> {
                submitData()
            }


        }


    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword. execute (state. password)
        val repeatedPasswordResult = validateRepeatedPassword. execute(
            state.password, state.repeatedPassword )


        val hasError = listOf(
            emailResult,
            passwordResult,
        repeatedPasswordResult
        ).any { it.successful }

        if(hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage
            )
            return
        }
viewModelScope.launch {
    validationEventChannel.send(ValidationEvent.Success)
}




    }


    var name by mutableStateOf(TextFieldValue())
    var email by  mutableStateOf(TextFieldValue())
    var password by  mutableStateOf(TextFieldValue())
    var confirmPassword by mutableStateOf(TextFieldValue())
    var phoneNumber by mutableStateOf(TextFieldValue())

    



    fun createUserInFirebase(email: String, password : String, navController: NavController, context: Context) {


      if (email.isNotEmpty() && password.isNotEmpty() )  {
            FirebaseAuth
                .getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    Log.d(TAG, "Inside _OnCompleteListener")
                    Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
                    if (it.isSuccessful) {
                        navController.navigate("login")
                    }

                }
                .addOnFailureListener {
                    Log.d(TAG, "Inside_OnFailureListener")
                    Log.d(TAG, "Exception= ${it.localizedMessage}")
                    Toast.makeText(context, "${it.localizedMessage}", Toast.LENGTH_LONG).show()
                }

        } else{

          Toast.makeText(context, "Please fill the form", Toast.LENGTH_LONG).show()


        }




    }


    sealed class ValidationEvent {
        object Success: ValidationEvent()}


}
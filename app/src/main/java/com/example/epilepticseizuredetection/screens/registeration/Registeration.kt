package com.example.epilepticseizuredetection.screens.registeration

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.epilepticseizuredetection.R
import com.example.epilepticseizuredetection.presentation.RegisterationUIEvent
import com.example.epilepticseizuredetection.presentation.viewModels.RegistrationViewModel
import com.example.epilepticseizuredetection.screens.DodgerBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController, registrationViewModel : RegistrationViewModel) {


    val state = registrationViewModel.state
   val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        registrationViewModel.validationEvents.collect { event ->
            when (event) {
                is RegistrationViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context, "registeration successful", Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }



    var name by remember { mutableStateOf(TextFieldValue()) }
    var email by remember{ mutableStateOf(TextFieldValue()) }
    var password by remember{ mutableStateOf(TextFieldValue()) }
    var confirmPassword by remember{ mutableStateOf(TextFieldValue()) }
    var phoneNumber by remember{ mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(16.dp)
        )



        OutlinedTextField(
            value = registrationViewModel.name,
            onValueChange = { registrationViewModel.name = it },
            label = { Text("Name") }
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = { registrationViewModel.onEvent(RegisterationUIEvent.EmailChanged(it)) },
            label = { Text("Email") },
            isError = state.emailError != null

        )
        if(state.emailError != null) {
        Text (
            text = state.emailError!!,
            color = MaterialTheme.colorScheme.error
        )
    }


        OutlinedTextField(
            value = state.password,
            onValueChange = { registrationViewModel.onEvent(RegisterationUIEvent.PasswordChanged(it)) },
            label = { Text("Password") },
            isError = state.passwordError != null,
            visualTransformation = PasswordVisualTransformation()
        )
        if(state.passwordError != null) {
            Text (
                text = state.passwordError!!,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            value = state.repeatedPassword,
            onValueChange = { registrationViewModel.onEvent(RegisterationUIEvent.RepeatedPasswordChanged(it))  },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        if(state.repeatedPasswordError != null) {
            Text (
                text = state.repeatedPasswordError!!,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            value = registrationViewModel.phoneNumber,
            onValueChange = { registrationViewModel.phoneNumber = it },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        )



       Spacer(modifier = Modifier.height(12.dp))


        Button(
            onClick = {
                    registrationViewModel.createUserInFirebase(state.email, state.password, navController, context)
                      },
            colors = ButtonDefaults.buttonColors(
                containerColor = DodgerBlue,
                contentColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text("Register")
        }
        

        
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegistrationScreen() {
  //RegistrationScreen()

}


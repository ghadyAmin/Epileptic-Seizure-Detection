package com.example.epilepticseizuredetection.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.epilepticseizuredetection.data.LoginUIState

class LoginViewModel : ViewModel() {

//fun login (email: String, password: String, navController : NavController){
//
//val auth = Firebase.auth
//
//    val currentUser = auth.currentUser
//    currentUser?.reload()
//
//
//
//    auth.signInWithEmailAndPassword(email, password)
//    .addOnCompleteListener {
//        Log.d(ContentValues.TAG, "Inside _OnCompleteListener")
//        Log.d(ContentValues.TAG, "isSuccessful = ${it.isSuccessful}")
//        if (it.isSuccessful){
//            navController.navigate("home_page")
//        } else{
//            Toast.makeText(
//                LocalContext.current,
//                "Authentication failed.",
//                Toast.LENGTH_SHORT,
//            ).show()
//
//        }
//
//    } .addOnFailureListener {
//        Log.d(ContentValues.TAG, "Inside_OnFailureListener")
//        Log.d(ContentValues.TAG, "Exception= $(it .message)")
//        Log.d(ContentValues.TAG, "Exception= ${it.localizedMessage}")
//    }
//
//
//}

    var uiState = mutableStateOf(LoginUIState())
        private set






}
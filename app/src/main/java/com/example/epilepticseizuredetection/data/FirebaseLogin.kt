package com.example.epilepticseizuredetection.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



fun login (email: String, password: String, navController : NavController, context: Context) {

    val auth = Firebase.auth

    val currentUser = auth.currentUser
    currentUser?.reload()

    if (email.isNotEmpty() && password.isNotEmpty() )  {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(ContentValues.TAG, "Inside _OnCompleteListener")
                Log.d(ContentValues.TAG, "isSuccessful = ${it.isSuccessful}")
                if (it.isSuccessful) { navController.navigate("home_page") }

            }.addOnFailureListener {
                Log.d(ContentValues.TAG, "Inside_OnFailureListener")
                Log.d(ContentValues.TAG, "Exception= $(it .message)")
                Log.d(ContentValues.TAG, "Exception= ${it.localizedMessage}")
                Toast.makeText(
                    context,
                    "${it.localizedMessage}",
                    Toast.LENGTH_LONG,
                ).show()

            }
    }else{

        Toast.makeText(context, "Please fill the form", Toast.LENGTH_LONG).show()


    }
}
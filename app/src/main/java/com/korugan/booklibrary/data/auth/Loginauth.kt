package com.korugan.booklibrary.data.auth

import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

private lateinit var auth: FirebaseAuth

fun loginAuth(email: String, password: String,navController: NavController) {
    auth = Firebase.auth
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            navController.navigate("mainFavorites"){
                popUpTo(0)
            }
        }
    }
}
fun haveCurrentUser(): String {
    if (Firebase.auth.currentUser != null){
        return "mainFavorites"
    }else{
        return "loginScreen"
    }
}

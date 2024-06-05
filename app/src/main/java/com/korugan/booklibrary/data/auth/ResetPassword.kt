package com.korugan.booklibrary.data.auth

import androidx.compose.runtime.MutableState
import com.google.firebase.auth.FirebaseAuth

fun resetPassword(email: MutableState<String>) {
    email.value = email.value.trim()
    val auth = FirebaseAuth.getInstance()
    if (email.value.isNotEmpty()){
        auth.sendPasswordResetEmail(email.value).addOnSuccessListener {

        }.addOnFailureListener {

        }
    }
}
package com.korugan.booklibrary.data.auth

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun registerAuth(username: String, email: String, password: String, navController: NavController) {
    if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = FirebaseAuth.getInstance().currentUser?.uid
                    val db = FirebaseFirestore.getInstance()
                    val user = hashMapOf(
                        "username" to username,
                        "email" to email,
                    )
                    userId?.let {
                        db.collection("users").document(it).set(user)
                            .addOnFailureListener {

                            }
                    }
                    loginAuth(email, password, navController)
                }
            }
    }
}
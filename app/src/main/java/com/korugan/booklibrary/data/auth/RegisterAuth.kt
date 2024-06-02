package com.korugan.booklibrary.data.auth

import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun registerAuth(username: String, email: String, password: String, navController: NavController) {
    if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = FirebaseAuth.getInstance().currentUser?.uid
                val db = FirebaseFirestore.getInstance()
                val user = hashMapOf(
                    "username" to username,
                    "email" to email
                )
                userId?.let {
                    db.collection("users").document(it).set(user)
                        .addOnFailureListener { e ->
                            Toast.makeText(null,"Error : $e",Toast.LENGTH_SHORT).show()
                        }
                }
                loginAuth(email,password,navController)
            } else {
                Toast.makeText(null,"Registration Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }else if (username.contains(" ")){
        Toast.makeText(null,"Username Incorrect", Toast.LENGTH_SHORT).show()
    }else if (email.contains(" ")){
        Toast.makeText(null,"Email Incorrect", Toast.LENGTH_SHORT).show()
    }else if (password.contains(" ")) {
        Toast.makeText(null, "Password Incorrect", Toast.LENGTH_SHORT).show()
    }
}
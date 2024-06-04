package com.korugan.booklibrary.data.user.get

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun getUserReading(callback: (List<String>) -> Unit) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    val userData = FirebaseFirestore.getInstance()
    val userCollection = userData.collection("users").document(userId!!).collection("reading")

    userCollection.get()
        .addOnSuccessListener { documents ->
            if (!documents.isEmpty) {
                val favorites = documents.mapNotNull { it.getString("bookId") }
                callback(favorites)
            } else {
                callback(emptyList())
            }
        }
        .addOnFailureListener {

        }
}

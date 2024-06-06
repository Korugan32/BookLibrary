package com.korugan.booklibrary.data.user.get

import com.google.firebase.firestore.FirebaseFirestore

fun getUserFavorites(userId:String,callback: (List<String>) -> Unit) {
    val userData = FirebaseFirestore.getInstance()
    val userCollection = userData.collection("users").document(userId!!).collection("favorites")

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

package com.korugan.booklibrary.data.user.friends

import com.google.firebase.firestore.FirebaseFirestore
import com.korugan.booklibrary.util.UserData

fun getUserData(userId: String, onUserDataReceived: (UserData) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val userRef = db.collection("users").document(userId)

    userRef.get().addOnSuccessListener { document ->
        if (document != null && document.exists()) {
            val username = document.getString("username") ?: ""
            val friends = document.get("friends") as? List<String> ?: emptyList()
            val favorites = document.get("favorites") as? List<String> ?: emptyList()
            val userData = UserData(userId,username, friends, favorites)
            onUserDataReceived(userData)
        }
    }.addOnFailureListener {

    }
}
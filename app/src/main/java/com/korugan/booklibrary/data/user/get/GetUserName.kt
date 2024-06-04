package com.korugan.booklibrary.data.user.get

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun getUsername(callback: (String?) -> Unit) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    if (userId == null) {
        callback(null)
        return
    }

    val userData = FirebaseFirestore.getInstance()
    val userCollection = userData.collection("users")

    userCollection.document(userId).get().addOnSuccessListener { document ->
        if (document != null && document.exists()) {
            val username = document.getString("username")
            callback(username)
        } else {
            callback(null)
        }
    }.addOnFailureListener {
        callback(null)
    }
}
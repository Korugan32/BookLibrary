package com.korugan.booklibrary.data.user.control

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


fun favoriteControl(bookId: String, callback: (Boolean) -> Unit) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val firestore = FirebaseFirestore.getInstance()
    val firestoreFav = firestore.collection("users").document(userId!!).collection("favorites")

    firestoreFav.whereEqualTo("bookId", bookId).get()
        .addOnSuccessListener { documents ->
            callback(documents.isEmpty)
        }
}
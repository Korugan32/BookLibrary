package com.korugan.booklibrary.data.user.control

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


fun readControl(bookId: String, callback: (Boolean) -> Unit) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val firestore = FirebaseFirestore.getInstance()
    val firestoreRead = firestore.collection("users").document(userId!!).collection("read")
    firestoreRead.whereEqualTo("bookId", bookId).get()
        .addOnSuccessListener { documents ->
            callback(documents.isEmpty)
        }
}
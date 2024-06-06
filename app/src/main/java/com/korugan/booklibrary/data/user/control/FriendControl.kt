package com.korugan.booklibrary.data.user.control

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun friendControl(friendId:String,callback: (Boolean) -> Unit){
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
    if (currentUserId == null) {
        println("Error: No current user is logged in.")
        callback(false)
        return
    }

    val firestore = FirebaseFirestore.getInstance()
    val currentUserRef = firestore.collection("users").document(currentUserId)

    currentUserRef.get().addOnSuccessListener { document ->
        if (document != null && document.exists()) {
            val friendsList = document.get("friends") as? List<String> ?: emptyList()
            callback(friendsList.contains(friendId))
        } else {
            callback(false)
        }
    }.addOnFailureListener { exception ->
        println("Error getting documents: $exception")
        callback(false)
    }
}
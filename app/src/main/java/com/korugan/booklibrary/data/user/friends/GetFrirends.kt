package com.korugan.booklibrary.data.user.friends

import com.google.firebase.firestore.FirebaseFirestore

fun getFriends(userId:String,callback:(List<String> )-> Unit) {
    val db = FirebaseFirestore.getInstance()

    if (userId != null) {
        val currentUserRef = db.collection("users").document(userId)

        currentUserRef.get().addOnSuccessListener { document ->
            if (document != null && document.exists()) {
                val friends = document.get("friends") as? List<String>
                if (friends != null) {
                    callback(friends)
                }
            }
        }.addOnFailureListener {

        }
    }
}

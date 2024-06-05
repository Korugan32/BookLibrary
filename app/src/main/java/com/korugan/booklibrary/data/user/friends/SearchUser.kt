package com.korugan.booklibrary.data.user.friends

import com.google.firebase.firestore.FirebaseFirestore
import com.korugan.booklibrary.util.UserData

fun searchUser(query: String, callback: (List<UserData>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("users")
        .whereEqualTo("username", query)
        .get()
        .addOnSuccessListener { documents ->
            val users = documents.map { doc ->
                val friends = doc.get("friends") as? List<String> ?: emptyList()
                val favorites = doc.get("favorites") as? List<String> ?: emptyList()
                val username = doc.getString("username") ?: ""
                UserData(doc.id, username, friends,favorites)
            }
            callback(users)
        }
        .addOnFailureListener { exception ->
            println("Error getting documents: $exception")
            callback(emptyList())
        }
}
package com.korugan.booklibrary.data.user.friends

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.korugan.booklibrary.data.user.control.friendControl

fun setFriend(friendUserId: String) {
    val db = FirebaseFirestore.getInstance()
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    val currentUserRef = db.collection("users").document(currentUserId!!)
    val friendUserRef = db.collection("users").document(friendUserId)

    friendControl(friendUserId) { isFriend ->
        if (isFriend) {
            db.runBatch { batch ->
                batch.update(currentUserRef, "friends", FieldValue.arrayRemove(friendUserId))
                batch.update(friendUserRef, "friends", FieldValue.arrayRemove(currentUserId))
            }.addOnSuccessListener {
                println("Friend successfully removed.")
            }.addOnFailureListener { exception ->
                println("Error removing friend: $exception")
            }
        } else {
            db.runBatch { batch ->
                batch.update(currentUserRef, "friends", FieldValue.arrayUnion(friendUserId))
                batch.update(friendUserRef, "friends", FieldValue.arrayUnion(currentUserId))
            }.addOnSuccessListener {
                println("Friend successfully added.")
            }.addOnFailureListener { exception ->
                println("Error adding friend: $exception")
            }
        }
    }
}

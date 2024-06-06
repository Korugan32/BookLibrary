package com.korugan.booklibrary.data.user.set

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.korugan.booklibrary.data.user.get.getUserRead

fun setUserRead(bookId: String) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val firestore = FirebaseFirestore.getInstance()
    val firestoreRead = firestore.collection("users").document(userId!!).collection("read")

    getUserRead {
        firestoreRead.whereEqualTo("bookId", bookId).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {

                    val readData = mapOf("bookId" to bookId)
                    firestoreRead.add(readData)
                        .addOnSuccessListener {

                        }
                        .addOnFailureListener {

                        }
                } else {
                    for (document in documents) {
                        firestoreRead.document(document.id).delete()
                            .addOnSuccessListener {

                            }
                            .addOnFailureListener {

                            }
                    }
                }
            }
            .addOnFailureListener {

            }
    }
}
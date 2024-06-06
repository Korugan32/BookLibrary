package com.korugan.booklibrary.data.user.set

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.korugan.booklibrary.data.user.get.getUserReading

fun setUserReading(bookId: String) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val firestore = FirebaseFirestore.getInstance()
    val firestoreReading = firestore.collection("users").document(userId!!).collection("reading")

    getUserReading {
        firestoreReading.whereEqualTo("bookId", bookId).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {

                    val readingData = mapOf("bookId" to bookId)
                    firestoreReading.add(readingData)
                        .addOnSuccessListener {

                        }
                        .addOnFailureListener {

                        }
                } else {
                    for (document in documents) {
                        firestoreReading.document(document.id).delete()
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
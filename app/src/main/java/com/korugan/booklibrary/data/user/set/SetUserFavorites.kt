package com.korugan.booklibrary.data.user.set

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.korugan.booklibrary.data.user.get.getUserFavorites

fun setUserFavorites(bookId: String) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val firestore = FirebaseFirestore.getInstance()
    val firestoreFav = firestore.collection("users").document(userId!!).collection("favorites")

    getUserFavorites(userId) {
        firestoreFav.whereEqualTo("bookId", bookId).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {

                    val favoriteData = mapOf("bookId" to bookId)
                    firestoreFav.add(favoriteData)
                        .addOnSuccessListener {

                        }
                        .addOnFailureListener {

                        }
                } else {
                    for (document in documents) {
                        firestoreFav.document(document.id).delete()
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

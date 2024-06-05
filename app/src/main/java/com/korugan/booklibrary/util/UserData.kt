package com.korugan.booklibrary.util

data class UserData(
    val id: String,
    val name: String,
    val friends: List<String> = emptyList(),
    val favorites: List<String> = emptyList(),
)
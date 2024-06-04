package com.korugan.booklibrary.util.api

import com.korugan.booklibrary.util.BookData
import org.json.JSONObject

fun parseSingleBookResponse(response: String): BookData? {
    return try {
        val jsonObject = JSONObject(response)
        val bookId = jsonObject.optString("id")
        val volumeInfo = jsonObject.optJSONObject("volumeInfo")

        val title = volumeInfo?.optString("title", "No title available") ?: "No title available"
        val authorsArray = volumeInfo?.optJSONArray("authors")
        val authors = if (authorsArray != null) {
            val authorList = mutableListOf<String>()
            for (i in 0 until authorsArray.length()) {
                authorList.add(authorsArray.getString(i))
            }
            authorList.joinToString(", ")
        } else {
            "No authors available"
        }

        val imageLinks = volumeInfo?.optJSONObject("imageLinks")
        val thumbnail = imageLinks?.optString("thumbnail", "No thumbnail available")

        thumbnail?.let {
            BookData(title, authors, it.replaceFirst("http://", "https://"), bookId)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
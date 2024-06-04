package com.korugan.booklibrary.util.api

import com.korugan.booklibrary.util.BookData
import org.json.JSONObject

fun parseJsonResponse(response: String): List<BookData> {
    val bookList = mutableListOf<BookData>()

    try {
        val jsonObject = JSONObject(response)
        val itemsArray = jsonObject.optJSONArray("items")

        if (itemsArray != null) {
            for (i in 0 until itemsArray.length()) {
                val item = itemsArray.getJSONObject(i)
                val bookId = item?.optString("id")
                val volumeInfo = item.optJSONObject("volumeInfo")

                val title = volumeInfo?.optString("title", "No title available") ?: "No title available"

                val authorsArray = volumeInfo?.optJSONArray("authors")
                val authors = if (authorsArray != null) {
                    val authorList = mutableListOf<String>()
                    for (j in 0 until authorsArray.length()) {
                        authorList.add(authorsArray.getString(j))
                    }
                    authorList.joinToString(", ")
                } else {
                    "No authors available"
                }

                val imageLinks = volumeInfo?.optJSONObject("imageLinks")
                val thumbnail = imageLinks?.optString("thumbnail", "No thumbnail available")

                if (thumbnail != null) {
                    bookList.add(BookData(title, authors, thumbnail.replaceFirst("http://", "https://"), bookId.toString()))
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return bookList
}
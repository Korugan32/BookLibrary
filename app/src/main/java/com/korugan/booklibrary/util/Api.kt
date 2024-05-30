package com.korugan.booklibrary.util

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

fun apiConnection(query: String, callback: (List<BookData>) -> Unit){
    val apiKey: String ="AIzaSyD72-ZXAjHGp8DTH5cLaBq_KpP9pi_ecgM"
    CoroutineScope(Dispatchers.IO).launch {
        val urlString = "https://www.googleapis.com/books/v1/volumes?q=$query&key=$apiKey"
        val url = URL(urlString)
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            urlConnection.requestMethod = "GET"
            urlConnection.connect()

            val responseCode = urlConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val responseStream = urlConnection.inputStream.bufferedReader().use { it.readText() }
                withContext(Dispatchers.Main) {
                    val bookList = parseJsonResponse(responseStream)
                    callback(bookList)
                }
            } else {
                Log.e("apiConnection", "Error: $responseCode")
            }
        } catch (e: Exception) {
            Log.e("apiConnection", "Exception: ${e.message}")
        } finally {
            urlConnection.disconnect()
        }
    }
}


private fun parseJsonResponse(response: String): List<BookData> {
    val bookList = mutableListOf<BookData>()

    try {
        val jsonObject = JSONObject(response)
        val itemsArray = jsonObject.optJSONArray("items")

        if (itemsArray != null) {
            for (i in 0 until itemsArray.length()) {
                val item = itemsArray.getJSONObject(i)
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
                val thumbnail = imageLinks?.optString("thumbnail", "No thumbnail available") ?: "No thumbnail available"

                bookList.add(BookData(title, authors, thumbnail))
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return bookList
}
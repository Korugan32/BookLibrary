package com.korugan.booklibrary.util.api

import android.util.Log
import com.korugan.booklibrary.util.BookData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

fun apiConnection(query: String, callback: (List<BookData>) -> Unit) {
    val apiKey = "AIzaSyCPEw7x0b8NibA-b29X920mYvdTtvhS1PQ"
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
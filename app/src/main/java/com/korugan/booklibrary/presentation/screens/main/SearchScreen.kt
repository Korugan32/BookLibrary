package com.korugan.booklibrary.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.korugan.booklibrary.presentation.components.Book
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.Purple
import com.korugan.booklibrary.util.BookData
import com.korugan.booklibrary.util.api.apiConnection


@Composable
fun SearchScreen(navController: NavHostController) {
    val query = remember { mutableStateOf("") }
    val books = remember { mutableStateOf<List<BookData>>(emptyList()) }
    apiConnection(query.value) { bookList ->
        books.value = bookList
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
            .statusBarsPadding(),
    ) {
        Row(Modifier.padding(vertical = 25.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = query.value, onValueChange = { query.value = it }, modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.White),
                maxLines = 1,
                label = { Text(text = "Search", color = Color.White) },
                trailingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = "", tint = Color.White) }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
        ) {
            items(books.value.chunked(3)) { chunkedBooks ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    chunkedBooks.forEach { book ->
                        Book(book, navController)
                    }
                }
            }
        }
    }

}
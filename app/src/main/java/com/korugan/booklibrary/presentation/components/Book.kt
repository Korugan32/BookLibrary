package com.korugan.booklibrary.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.korugan.booklibrary.util.BookData

@Composable
fun Book(book: BookData,navController: NavController) {
        Column(Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            Image(painter = rememberAsyncImagePainter(model = book.thumbnail), contentDescription = "", modifier = Modifier
                .height(140.dp)
                .width(130.dp).clickable {
                navController.navigate("mainBookInfo" + "?bookTitle=${book.title}&bookAuthor=${book.author}&bookImage=${book.thumbnail}&bookId=${book.bookId}")
                })
        }
}
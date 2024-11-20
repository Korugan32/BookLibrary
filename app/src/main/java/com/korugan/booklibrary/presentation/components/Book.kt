package com.korugan.booklibrary.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.korugan.booklibrary.util.BookData

@Composable
fun Book(book: BookData, navController: NavController) {
    val defaultColor = MaterialTheme.colorScheme.secondaryContainer

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(book.thumbnail)
            .size(Size.ORIGINAL)
            .build()
    ).state

    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(11.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.DarkGray,
                        dominantColor
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (imageState is AsyncImagePainter.State.Success) {
            dominantColor = getAverageColor(
                imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
            )
            Image(
                painter = rememberAsyncImagePainter(model = book.thumbnail), contentDescription = "", modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .height(230.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        navController.navigate(
                            "mainBookInfo" + "?bookTitle=${book.title}&bookAuthor=${book.author}&bookImage=${book.thumbnail}&bookId=${book.bookId}"
                        )
                    },
                contentScale = ContentScale.Crop
            )
        }
        Text(text = book.title, color = Color.White, textAlign = TextAlign.Center, fontSize = 15.sp)
    }
}
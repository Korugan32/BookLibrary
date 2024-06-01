package com.korugan.booklibrary.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.BookLibraryTheme
import com.korugan.booklibrary.presentation.theme.Purple


@Composable
fun FavoriteScreen(){
Column(modifier = Modifier.fillMaxSize().background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))) {

}
}

@Preview
@Composable
fun FavoriteScreenPreview(){
BookLibraryTheme {
    FavoriteScreen()
}
}
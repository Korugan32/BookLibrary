package com.korugan.booklibrary.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.korugan.booklibrary.presentation.theme.BookLibraryTheme


@Composable
fun LoginScreen(){
    Column (
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
    ){

    }
}

@Preview
@Composable
fun LoginPreview(){
    BookLibraryTheme {
        LoginScreen()
    }
}
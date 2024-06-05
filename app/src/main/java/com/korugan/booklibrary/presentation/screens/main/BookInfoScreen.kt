package com.korugan.booklibrary.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.korugan.booklibrary.data.user.control.favoriteControl
import com.korugan.booklibrary.data.user.control.readControl
import com.korugan.booklibrary.data.user.control.readingControl
import com.korugan.booklibrary.data.user.set.setUserFavorites
import com.korugan.booklibrary.data.user.set.setUserRead
import com.korugan.booklibrary.data.user.set.setUserReading
import com.korugan.booklibrary.presentation.components.Header
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.Purple

@Composable
fun BookInfoScreen(
    navController: NavController,
    bookTitle: String,
    bookAuthor: String,
    bookImage: String,
    bookId: String,
) {
    val favoritesText = remember { mutableStateOf("Add Favorites") }
    val readText = remember { mutableStateOf("Add I Read") }
    val readingText = remember { mutableStateOf("Add I Reading") }

    val favoritesIcon = remember { mutableStateOf(Icons.Outlined.FavoriteBorder) }
    val readIcon = remember { mutableStateOf(Icons.Outlined.AccessTime) }
    val readingIcon = remember { mutableStateOf(Icons.Outlined.CheckCircle) }

    val favoritesIconColor = remember { mutableStateOf(Color.White) }

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(bookId) {
        readControl(bookId) { isHave ->
            if (!isHave) {
                readText.value = "Remove I Read"
                readIcon.value = Icons.Filled.AccessTime
            }
            isLoading = false
        }

        readingControl(bookId) { isHave ->
            if (!isHave) {
                readingText.value = "Remove I Reading"
                readingIcon.value = Icons.Filled.CheckCircle
            }
            isLoading = false
        }

        favoriteControl(bookId) { isHave ->
            if (!isHave) {
                favoritesText.value = "Remove Favorites"
                favoritesIcon.value = Icons.Filled.Favorite
                favoritesIconColor.value = Color.Red
            }
            isLoading = false
        }
    }

    if (isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .size(300.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = "$bookImage&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = bookTitle, color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.W500, textAlign = TextAlign.Center)
                Text(text = bookAuthor, color = Color.LightGray, fontSize = 20.sp, fontWeight = FontWeight.W500)
            }
            Spacer(modifier = Modifier.padding(15.dp))
            Column(Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .clickable {
                                setUserFavorites(bookId)
                                navController.navigate("mainFavorites") {
                                    popUpTo(0)
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = favoritesIcon.value, contentDescription = "", modifier = Modifier.size(40.dp), tint = favoritesIconColor.value)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = favoritesText.value, fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.W500)
                    }
                }
                Spacer(modifier = Modifier.padding(15.dp))
                Row(
                    modifier = Modifier
                        .clickable {
                            setUserRead(bookId)
                            navController.navigate("mainRead") {
                                popUpTo(0)
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = readIcon.value, contentDescription = "", modifier = Modifier.size(40.dp), tint = Color.White)
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = readText.value, fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.W500)
                }
                Spacer(modifier = Modifier.padding(15.dp))
                Row(
                    modifier = Modifier
                        .clickable {
                            setUserReading(bookId)
                            navController.navigate("mainReading") {
                                popUpTo(0)
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = readingIcon.value, contentDescription = "", modifier = Modifier.size(40.dp), tint = Color.White)
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = readingText.value, fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.W500)
                }
            }
        }
    }
}

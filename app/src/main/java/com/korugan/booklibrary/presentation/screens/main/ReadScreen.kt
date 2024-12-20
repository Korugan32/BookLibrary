package com.korugan.booklibrary.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.korugan.booklibrary.data.user.get.getUserRead
import com.korugan.booklibrary.presentation.components.Book
import com.korugan.booklibrary.presentation.components.BottomBar
import com.korugan.booklibrary.presentation.components.Header
import com.korugan.booklibrary.presentation.screens.auth.screenWidth
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.Purple
import com.korugan.booklibrary.presentation.theme.Purple40
import com.korugan.booklibrary.util.BookData
import com.korugan.booklibrary.util.api.getBook

@Composable
fun ReadScreen(navController: NavHostController){
    val books = remember { mutableStateOf<List<BookData>>(emptyList()) }
    val isLoading = remember { mutableStateOf(true) }
    val isEmpty = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        getUserRead { bookIds ->
            if (bookIds.isEmpty()){
                isEmpty.value = true
            }
            val bookList = mutableListOf<BookData>()
            bookIds.forEach { id ->
                getBook(id) { book ->
                    book?.let {
                        bookList.add(it)
                        books.value = bookList.sortedBy { book -> book.title }
                    }
                    if (bookList.size==bookIds.size){
                        isLoading.value = false
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
            .statusBarsPadding(),
    ) {
        Scaffold(
            topBar = { Header(FirebaseAuth.getInstance().currentUser!!.uid,navController) },
            containerColor = DefaultTintColor,
            bottomBar = { BottomBar( navController) },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.size(60.dp),
                    containerColor = Purple40,
                    onClick = { navController.navigate("mainSearch") },
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier.size(30.dp),
                    )
                }
            },
        ) { paddingValues ->
            if (isEmpty.value){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Filled.NotInterested, contentDescription = "", tint = Color.Gray, modifier = Modifier.size(200.dp))
                    Text(text = "No Read Books Found", color = Color.LightGray, fontSize = screenWidth() *0.08.sp)
                }
            }else {
                if (isLoading.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
                            .padding(paddingValues),
                        columns = GridCells.Fixed(3)
                    ) {
                        items(books.value.size){
                            Book(books.value[it],navController)
                        }
                    }
                }
            }
        }
    }
}
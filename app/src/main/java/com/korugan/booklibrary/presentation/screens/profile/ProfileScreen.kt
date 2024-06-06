package com.korugan.booklibrary.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.korugan.booklibrary.data.user.friends.getFriends
import com.korugan.booklibrary.data.user.friends.getUserData
import com.korugan.booklibrary.data.user.get.getUserFavorites
import com.korugan.booklibrary.presentation.components.Book
import com.korugan.booklibrary.presentation.components.User
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.Purple
import com.korugan.booklibrary.util.BookData
import com.korugan.booklibrary.util.UserData
import com.korugan.booklibrary.util.api.getBook

@Composable
fun ProfileScreen(
    navController: NavHostController,
    userId: String,
    userName: String,
) {
    val selected = remember { mutableIntStateOf(0) }
    val favoritesColor = remember { mutableStateOf(Color.White) }
    val friendsColor = remember { mutableStateOf(Color.LightGray) }
    val friendsList = remember { mutableStateListOf<String>() }
    val friendsData = remember { mutableStateListOf<UserData>() }
    val books = remember { mutableStateOf<List<BookData>>(emptyList()) }
    val isLoading = remember { mutableStateOf(true) }
    val isEmpty = remember { mutableStateOf(false) }

    if (selected.intValue == 0) {
        getUserFavorites(userId) { bookIds ->
            if (bookIds.isEmpty()) {
                isEmpty.value = true
            }
            val bookList = mutableListOf<BookData>()
            bookIds.forEach { id ->
                getBook(id) { book ->
                    book?.let {
                        bookList.add(it)
                        books.value = bookList.sortedBy { book -> book.title }
                    }
                    if (bookList.size == bookIds.size) {
                        isLoading.value = false
                    }
                }
            }
        }
    } else {
        getFriends(FirebaseAuth.getInstance().currentUser!!.uid) { friends ->
            friendsList.clear()
            friendsList.addAll(friends)
            friendsData.clear()
            friends.forEach { friendId ->
                getUserData(friendId) { user ->
                    friendsData.add(user)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Blue, Purple)))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(90.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "", tint = Color.White, modifier = Modifier
                .size(40.dp)
                .clickable { navController.popBackStack() })
            Icon(imageVector = Icons.Outlined.Person, contentDescription = "", tint = Color.White, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = userName, color = Color.White, fontSize = 30.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "Favorites", color = favoritesColor.value, fontSize = 15.sp, modifier = Modifier.clickable {
                favoritesColor.value = friendsColor.value;
                friendsColor.value = Color.LightGray;
                selected.value = 0
            })
            Text(text = "Friends", color = friendsColor.value, fontSize = 15.sp, modifier = Modifier.clickable {
                friendsColor.value = favoritesColor.value;
                favoritesColor.value = Color.LightGray;
                selected.value = 1
            })
        }
        Spacer(modifier = Modifier.padding(10.dp))
        if (isEmpty.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(imageVector = Icons.Filled.NotInterested, contentDescription = "", tint = Color.Gray, modifier = Modifier.size(200.dp))
                if (selected.value == 0) {
                    Text(text = "No Favorites Books Found", color = Color.LightGray, fontSize = 30.sp)
                } else {
                    Text(text = "No Friends Found", color = Color.LightGray, fontSize = 30.sp)
                }
            }
        } else {
            if (isLoading.value) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
                        .padding()
                ) {
                    if (selected.value == 0) {
                        items(books.value.chunked(3)) { book ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                book.forEach { book ->
                                    Book(book, navController)
                                }
                            }
                        }
                    } else {
                        items(friendsData) { friend ->
                            User(friend, navController)
                        }
                    }
                }
            }
        }
    }
}
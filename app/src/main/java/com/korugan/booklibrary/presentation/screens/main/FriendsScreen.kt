package com.korugan.booklibrary.presentation.screens.main

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import com.korugan.booklibrary.data.user.friends.getFriends
import com.korugan.booklibrary.data.user.friends.getUserData
import com.korugan.booklibrary.presentation.components.User
import com.korugan.booklibrary.presentation.screens.auth.screenWidth
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.Purple
import com.korugan.booklibrary.presentation.theme.Purple40
import com.korugan.booklibrary.util.UserData

@Composable
fun FriendsScreen(navController: NavHostController) {
    val friendsList = remember { mutableStateListOf<String>() }
    val friendsData = remember { mutableStateListOf<UserData>() }

    LaunchedEffect(Unit) {
        getFriends { friends ->
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
            .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
            .statusBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenWidth() * 0.16.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "", tint = Color.White, modifier = Modifier.size(35.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Icon(imageVector = Icons.Outlined.PeopleOutline, contentDescription = "", tint = Color.White, modifier = Modifier.size(35.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "Friends",
                    color = Color.White,
                    fontSize = screenWidth() * 0.06.sp,
                    fontWeight = FontWeight.W500,
                )
            }
        }
        Scaffold(
            containerColor = DefaultTintColor,
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.size(60.dp),
                    containerColor = Purple40,
                    onClick = { navController.navigate("mainSearchUser")},
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier.size(30.dp),
                    )
                }
            },
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
                    .padding(paddingValues)
            ) {
                items(friendsData){ friend ->
                    User(user = friend)
                }
            }
        }
    }
}

package com.korugan.booklibrary.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import com.google.firebase.auth.FirebaseAuth
import com.korugan.booklibrary.data.user.friends.searchUser
import com.korugan.booklibrary.presentation.components.User
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.Purple
import com.korugan.booklibrary.util.UserData

@Composable
fun SearchUser(navController: NavHostController) {
    val query = remember { mutableStateOf("") }
    val usersData = remember { mutableStateOf<List<UserData>>(emptyList()) }

    searchUser(query.value) { users ->
        users.forEach { user ->
            usersData.value = listOf(user)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
            .statusBarsPadding(),
    ) {
        Row(
            Modifier.padding(vertical = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack, contentDescription = "", tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.popBackStack()
                    },
            )
            Spacer(modifier = Modifier.padding(5.dp))
            OutlinedTextField(
                value = query.value, onValueChange = { query.value = it },
                modifier = Modifier.width(360.dp),
                textStyle = TextStyle(color = Color.White),
                maxLines = 1,
                label = { Text(text = "Search", color = Color.White) },
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
        ) {
            items(usersData.value) { user ->
                if (user.id != FirebaseAuth.getInstance().currentUser!!.uid){
                        User(user,navController)
                }
            }
        }
    }
}


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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.korugan.booklibrary.data.user.get.getUsername
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.Purple

@Composable
fun UserProfileScreen(navController: NavHostController) {
    val userName = remember {
        mutableStateOf("")
    }
    getUsername(FirebaseAuth.getInstance().currentUser!!.uid) { username ->
        userName.value = username!!
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
            Text(text = userName.value, color = Color.White, fontSize = 30.sp)
        }
        Row (modifier = Modifier.padding(10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
            ){
            Text(text = "Sign Out", color = Color.White, fontSize = 25.sp, modifier = Modifier.clickable {
                FirebaseAuth.getInstance().signOut()
            })
            Icon(imageVector = Icons.Outlined.Logout, contentDescription ="", tint = Color.White )
        }
    }
}
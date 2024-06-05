package com.korugan.booklibrary.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import com.korugan.booklibrary.presentation.screens.auth.screenWidth

@Composable
fun BottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenWidth() * 0.21.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "",
            modifier = Modifier
                .size(screenWidth()*0.1.dp)
                .clickable { navController.navigate("mainFavorites") },
            tint = Color.White,
        )
        Icon(
            imageVector = Icons.Outlined.AccessTime,
            contentDescription = "",
            modifier = Modifier
                .size(screenWidth()*0.1.dp)
                .clickable { navController.navigate("mainReading") },
            tint = Color.White,
        )
        Icon(
            imageVector = Icons.Outlined.CheckCircle,
            contentDescription = "",
            modifier = Modifier
                .size(screenWidth()*0.1.dp)
                .clickable { navController.navigate("mainRead") },
            tint = Color.White,
        )
        Icon(
            imageVector = Icons.Outlined.People,
            contentDescription = "",
            modifier = Modifier
                .size(screenWidth()*0.1.dp)
                .clickable { navController.navigate("mainFriends") },
            tint = Color.White,
        )
    }
}
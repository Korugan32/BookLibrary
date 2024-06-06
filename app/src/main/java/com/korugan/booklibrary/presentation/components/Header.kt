package com.korugan.booklibrary.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import com.korugan.booklibrary.R
import com.korugan.booklibrary.data.user.get.getUsername
import com.korugan.booklibrary.presentation.screens.auth.screenWidth

@Composable
fun Header(userId:String,navController: NavHostController) {
    val username = remember {
        mutableStateOf<String?>(null)
    }
    getUsername (userId){ fetchedUsername ->
        username.value = fetchedUsername
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenWidth() * 0.16.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.profile_default_svgrepo_com),
                contentDescription = "",
                modifier = Modifier
                    .size(screenWidth() * 0.09.dp)
                    .clickable { navController.navigate("profileUserScreen")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = username.value.toString(),
                color = Color.White,
                fontSize = screenWidth() * 0.06.sp,
                fontWeight = FontWeight.W500,
            )
        }
    }
}
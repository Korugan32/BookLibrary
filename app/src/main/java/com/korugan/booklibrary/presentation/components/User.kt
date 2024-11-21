package com.korugan.booklibrary.presentation.components

import com.korugan.booklibrary.data.user.friends.setFriend
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonAddAlt
import androidx.compose.material.icons.outlined.PersonRemoveAlt1
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.korugan.booklibrary.data.user.control.friendControl
import com.korugan.booklibrary.util.UserData

@Composable
fun User(user:UserData,navController:NavHostController) {
    val isFriendIcon = remember { mutableStateOf(Icons.Outlined.PersonAddAlt) }

    LaunchedEffect(Unit) {
        friendControl(user.id){
            if (it){
                isFriendIcon.value = Icons.Outlined.PersonRemoveAlt1
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(imageVector = Icons.Outlined.Person, contentDescription = "", tint = Color.White, modifier = Modifier.size(40.dp).clickable { navController.navigate("profileScreen"+"?userId=${user.id}&userName=${user.name}") })
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = user.name, color = Color.White, fontSize = 30.sp)
        Row (modifier = Modifier.fillMaxWidth(), Arrangement.End){
            Icon(imageVector = isFriendIcon.value, contentDescription = "", tint = Color.White,modifier = Modifier.size(40.dp).clickable { setFriend(user.id); navController.navigate("mainFavorites") })
        }
    }
}
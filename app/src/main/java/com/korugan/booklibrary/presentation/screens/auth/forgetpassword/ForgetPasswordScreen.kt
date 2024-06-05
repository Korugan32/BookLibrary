package com.korugan.booklibrary.presentation.screens.auth.forgetpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import com.korugan.booklibrary.data.auth.resetPassword
import com.korugan.booklibrary.presentation.screens.auth.screenWidth
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.Purple

@Composable
fun FPasswordScreen(navController: NavHostController) {
    val email = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = listOf(Blue, Purple)))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenWidth() * 0.10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(text = "Forget Password", style = TextStyle(color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.W500))
                Text(text = "Enter your email for verification process we will send reset link to your mail", color = Color.White)
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = email.value, onValueChange = { email.value = it }, modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    label = { Text(text = "Email", color = Color.White) },
                    trailingIcon = { Icon(imageVector = Icons.Outlined.Email, contentDescription = "", tint = Color.White) }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Button(
                    onClick = {
                        resetPassword(email)
                        navController.navigate("passwordCodeVerification")
                              },
                    modifier = Modifier
                        .width(screenWidth() * 0.56.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue),
                ) {
                    Text(text = "Send to Email")
                }
            }
        }
    }
}
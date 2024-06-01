package com.korugan.booklibrary.presentation.screens.auth.forgetpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import com.korugan.booklibrary.presentation.screens.auth.screenWidth
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.Purple

@Composable
fun ResetPassword(navController: NavHostController) {
    val password = remember {
        mutableStateOf("")
    }
    val confirmPassword = remember {
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
                Text(text = "Reset Password", style = TextStyle(color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.W500))
                Text(text = "Set the new password for your account ", color = Color.White)
                OutlinedTextField(
                    value = password.value, onValueChange = { password.value = it }, modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = TextStyle(color = Color.White),
                    label = { Text(text = "Password", color = Color.White) },
                    trailingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = "", tint = Color.White) }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = confirmPassword.value, onValueChange = { confirmPassword.value = it }, modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = TextStyle(color = Color.White),
                    label = { Text(text = "Confirm Password", color = Color.White) },
                    trailingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = "", tint = Color.White) }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Button(
                    onClick = {
                        navController.navigate("loginScreen") {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .width(screenWidth() * 0.56.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue),
                ) {
                    Text(text = "Reset Password")
                }
            }
        }
    }
}
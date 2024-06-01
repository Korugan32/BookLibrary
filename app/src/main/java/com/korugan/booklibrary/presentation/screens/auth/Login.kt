package com.korugan.booklibrary.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.LightBlue
import com.korugan.booklibrary.presentation.theme.Purple

@Composable
fun LoginScreen(navController: NavController) {
    val username = remember {
        mutableStateOf("")
    }
    val password = remember {
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
                Text(text = "Login", style = TextStyle(color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.W500))
                OutlinedTextField(
                    value = username.value, onValueChange = { username.value = it }, modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    label = { Text(text = "Username", color = Color.White) },
                    trailingIcon = { Icon(imageVector = Icons.Outlined.Person, contentDescription = "", tint = Color.White) }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = password.value, onValueChange = { password.value = it }, modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    label = { Text(text = "Password", color = Color.White) },
                    trailingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = "", tint = Color.White) }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                ClickableText(
                    text = AnnotatedString("Forget Password?"),
                    style = TextStyle(color = LightBlue),
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        navController.navigate("forgetPasswordScreen")
                    })
                Spacer(modifier = Modifier.padding(5.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "Don't Have An Account", fontSize = 13.sp, color = Color.White)
                    ClickableText(
                        text = AnnotatedString("Sign Up"),
                        modifier = Modifier.padding(horizontal = 6.dp),
                        style = TextStyle(color = LightBlue, fontSize = 14.5.sp),
                        onClick = {
                            navController.navigate("signUpScreen")
                        })
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(screenWidth() * 0.56.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue),
                ) {
                    Text(text = "Sign In")
                }
            }
        }

    }
}

@Composable
fun screenWidth(): Int = LocalConfiguration.current.screenWidthDp
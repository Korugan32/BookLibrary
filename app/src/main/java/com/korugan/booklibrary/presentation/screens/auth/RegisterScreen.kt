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
import androidx.compose.material.icons.outlined.Email
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.korugan.booklibrary.data.auth.registerAuth
import com.korugan.booklibrary.presentation.theme.Blue
import com.korugan.booklibrary.presentation.theme.LightBlue
import com.korugan.booklibrary.presentation.theme.Purple

@Composable
fun SignUpScreen(navController: NavController) {
    val regUsername = remember { mutableStateOf("") }
    val regEmail = remember { mutableStateOf("") }
    val regPassword = remember { mutableStateOf("") }
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
                Text(text = "Sign Up", style = TextStyle(color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.W500))
                OutlinedTextField(
                    value = regUsername.value, onValueChange = { regUsername.value = it }, modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    label = { Text(text = "Username", color = Color.White) },
                    trailingIcon = { Icon(imageVector = Icons.Outlined.Person, contentDescription = "", tint = Color.White) }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = regEmail.value, onValueChange = { regEmail.value = it }, modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    label = { Text(text = "Email", color = Color.White) },
                    trailingIcon = { Icon(imageVector = Icons.Outlined.Email, contentDescription = "", tint = Color.White) }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = regPassword.value, onValueChange = { regPassword.value = it }, modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    label = { Text(text = "Password", color = Color.White) },
                    trailingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = "", tint = Color.White) }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "Already Have An Account", fontSize = 13.sp, color = Color.White)
                    ClickableText(
                        text = AnnotatedString("Sign In"),
                        modifier = Modifier.padding(horizontal = 6.dp),
                        style = TextStyle(color = LightBlue, fontSize = 14.5.sp),
                        onClick = {
                            navController.popBackStack()
                        })
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Button(
                    onClick = { registerAuth(regUsername.value, regEmail.value, regPassword.value, navController) },
                    modifier = Modifier
                        .width(screenWidth() * 0.56.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue),
                ) {
                    Text(text = "Sign Up")
                }
            }
        }
    }
}
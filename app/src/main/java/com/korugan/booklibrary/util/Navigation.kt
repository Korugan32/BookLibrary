package com.korugan.booklibrary.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.korugan.booklibrary.presentation.screens.auth.forgetpassword.FPasswordScreen
import com.korugan.booklibrary.presentation.screens.auth.LoginScreen
import com.korugan.booklibrary.presentation.screens.auth.SignUpScreen
import com.korugan.booklibrary.presentation.screens.auth.forgetpassword.CodeVerificationScreen
import com.korugan.booklibrary.presentation.screens.auth.forgetpassword.ResetPassword

@Composable
fun Navigation(){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "loginScreen"){
        composable(route = "loginScreen"){
            LoginScreen(navController)
        }
        composable(route = "signUpScreen"){
            SignUpScreen(navController)
        }
        composable(route = "forgetPasswordScreen"){
            FPasswordScreen(navController)
        }
        composable(route = "passwordCodeVerification"){
            CodeVerificationScreen(navController)
        }
        composable(route = "resetPassword"){
            ResetPassword(navController)
        }
    }
}
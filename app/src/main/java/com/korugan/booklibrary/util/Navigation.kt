package com.korugan.booklibrary.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.korugan.booklibrary.presentation.screens.CodeVerificationScreen
import com.korugan.booklibrary.presentation.screens.FPasswordScreen
import com.korugan.booklibrary.presentation.screens.LoginScreen
import com.korugan.booklibrary.presentation.screens.ResetPassword
import com.korugan.booklibrary.presentation.screens.SignUpScreen

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
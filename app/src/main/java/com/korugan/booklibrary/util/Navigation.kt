package com.korugan.booklibrary.util

import com.korugan.booklibrary.presentation.screens.main.FavoriteScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.korugan.booklibrary.data.auth.haveCurrentUser
import com.korugan.booklibrary.presentation.screens.auth.forgetpassword.FPasswordScreen
import com.korugan.booklibrary.presentation.screens.auth.LoginScreen
import com.korugan.booklibrary.presentation.screens.auth.SignUpScreen
import com.korugan.booklibrary.presentation.screens.auth.forgetpassword.ResetPasswordDoneScreen
import com.korugan.booklibrary.presentation.screens.main.BookInfoScreen
import com.korugan.booklibrary.presentation.screens.main.FriendsScreen
import com.korugan.booklibrary.presentation.screens.main.ReadScreen
import com.korugan.booklibrary.presentation.screens.main.ReadingScreen
import com.korugan.booklibrary.presentation.screens.main.SearchScreen
import com.korugan.booklibrary.presentation.screens.main.SearchUser
import com.korugan.booklibrary.presentation.screens.profile.ProfileScreen
import com.korugan.booklibrary.presentation.screens.profile.UserProfileScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = haveCurrentUser()) {
        composable(route = "loginScreen") {
            LoginScreen(navController)
        }
        composable(route = "signUpScreen") {
            SignUpScreen(navController)
        }
        composable(route = "forgetPasswordScreen") {
            FPasswordScreen(navController)
        }
        composable(route = "passwordCodeVerification") {
            ResetPasswordDoneScreen(navController)
        }
        composable(route = "mainFavorites") {
            FavoriteScreen(navController)
        }
        composable(route = "mainReading") {
            ReadingScreen(navController)
        }
        composable(route = "mainRead") {
            ReadScreen(navController)
        }
        composable(
            route = "mainBookInfo" + "?bookTitle={bookTitle}&bookAuthor={bookAuthor}&bookImage={bookImage}&bookId={bookId}",
            arguments = listOf(
                navArgument(name = "bookTitle") {
                    type = NavType.StringType
                },
                navArgument(name = "bookAuthor") {
                    type = NavType.StringType
                },
                navArgument(name = "bookImage") {
                    type = NavType.StringType
                },
                navArgument(name = "bookId") {
                    type = NavType.StringType
                }
            )
        ) {
            val bookTitle = it.arguments?.getString("bookTitle")!!
            val bookAuthor = it.arguments?.getString("bookAuthor")!!
            val bookImage = it.arguments?.getString("bookImage")!!
            val bookId = it.arguments?.getString("bookId")!!
            BookInfoScreen(navController, bookTitle, bookAuthor, bookImage, bookId)
        }
        composable(route = "mainSearch") {
            SearchScreen(navController)
        }
        composable(route = "mainFriends") {
            FriendsScreen(navController)
        }
        composable(route = "mainSearchUser") {
            SearchUser(navController)
        }
        composable(route = "profileScreen"+"?userId={userId}&userName={userName}",
            arguments = listOf(
                navArgument(name = "userId"){
                    type = NavType.StringType
                },
                navArgument(name = "userName"){
                    type = NavType.StringType
                }
            )
            ) {
            val userId = it.arguments?.getString("userId")!!
            val userName = it.arguments?.getString("userName")!!
            ProfileScreen(navController,userId,userName)
        }
        composable(route = "profileUserScreen") {
            UserProfileScreen(navController)
        }
    }
}
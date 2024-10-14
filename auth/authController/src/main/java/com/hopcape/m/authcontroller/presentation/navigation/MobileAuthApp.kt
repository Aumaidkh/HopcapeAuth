package com.hopcape.m.authcontroller.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hopcape.m.common.navigation.AppDestinations
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.emailPasswordAuthNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileAuthApp(
    navController: NavHostController,
    startDestination: AppDestinations
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = startDestination.route
        ) {
            emailPasswordAuthNavigation(navController,snackbarHostState)
            composable(
                route = AppDestinations.RegisterScreen.route
            ) {

            }
            composable(
                route = AppDestinations.RequestOtp.route
            ) {

            }
            composable(
                route = AppDestinations.VerifyOtp.route
            ) {

            }
        }
    }
}
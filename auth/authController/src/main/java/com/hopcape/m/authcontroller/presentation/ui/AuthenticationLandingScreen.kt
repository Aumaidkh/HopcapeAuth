package com.hopcape.m.authcontroller.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.hopcape.m.authcontroller.presentation.navigation.MobileAuthApp
import com.hopcape.m.common.navigation.AppDestinations
import com.hopcape.m.designsystem.theme.MobileAuthTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationLandingScreen: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MobileAuthTheme {
                MobileAuthApp(
                    navController = navController,
                    startDestination = resolveStartDestination()
                )
            }
        }
    }

    private fun resolveStartDestination(): AppDestinations {
        return AppDestinations.LandingScreen
    }
}
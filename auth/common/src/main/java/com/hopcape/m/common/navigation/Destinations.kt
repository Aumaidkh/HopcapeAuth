package com.hopcape.m.common.navigation

sealed class AppDestinations(val route: String) {

    // Email Password Auth
    data object LandingScreen: AppDestinations("landing_screen")
    data object RegisterScreen: AppDestinations("register_screen")
    data object ForgotPasswordScreen: AppDestinations("forgot_password_screen")

    // Phone Auth
    data object RequestOtp: AppDestinations("request_otp")
    data object VerifyOtp: AppDestinations("verify_otp")
}
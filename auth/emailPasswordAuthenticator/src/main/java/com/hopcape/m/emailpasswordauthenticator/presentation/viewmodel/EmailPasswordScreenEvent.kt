package com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel

import com.hopcape.m.common.navigation.AppDestinations
import com.hopcape.m.common.viewmodel.ScreenEvent

sealed interface EmailPasswordScreenEvent: ScreenEvent {
    data class Error(val message: String): EmailPasswordScreenEvent
    data object Success: EmailPasswordScreenEvent
    data class Navigate(val route: AppDestinations): EmailPasswordScreenEvent
}
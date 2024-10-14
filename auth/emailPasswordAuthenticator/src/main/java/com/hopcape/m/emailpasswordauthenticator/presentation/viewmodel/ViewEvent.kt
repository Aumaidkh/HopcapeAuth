package com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel

import com.hopcape.m.common.navigation.AppDestinations
import com.hopcape.m.common.viewmodel.ScreenEvent

sealed interface ViewEvent: ScreenEvent {
    data class Error(val message: String): ViewEvent
    data object Success: ViewEvent
    data class Navigate(val route: AppDestinations): ViewEvent
}
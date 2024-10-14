package com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password

import com.hopcape.m.common.navigation.AppDestinations
import com.hopcape.m.common.viewmodel.ScreenEvent

sealed interface ResetPasswordEvent : ScreenEvent {
    data object Success : ResetPasswordEvent
    data class Error(val message: String) : ResetPasswordEvent
    data class Navigate(val route: AppDestinations? = null,val goBack: Boolean = false) : ResetPasswordEvent
}
package com.hopcape.m.emailpasswordauthenticator.presentation.ui.register

import com.hopcape.m.common.viewmodel.ScreenEvent

sealed interface RegisterScreenEvent: ScreenEvent {
    data class Error(val message: String): RegisterScreenEvent
    data object NavigateToLogin: RegisterScreenEvent
}
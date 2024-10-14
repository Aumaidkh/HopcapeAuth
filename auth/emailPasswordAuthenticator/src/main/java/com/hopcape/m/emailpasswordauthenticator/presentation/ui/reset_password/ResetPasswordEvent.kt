package com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password

import com.hopcape.m.common.viewmodel.ScreenEvent

sealed interface ResetPasswordEvent : ScreenEvent {
    data object Success : ResetPasswordEvent
    data class Error(val message: String) : ResetPasswordEvent
    data object NavigateBack: ResetPasswordEvent
}
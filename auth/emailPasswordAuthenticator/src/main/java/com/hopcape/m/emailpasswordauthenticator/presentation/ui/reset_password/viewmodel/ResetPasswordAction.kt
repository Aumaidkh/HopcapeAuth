package com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.viewmodel

import com.hopcape.m.common.viewmodel.UserAction

sealed interface ResetPasswordAction: UserAction {
    data class OnEmailChange(val email: String): ResetPasswordAction
    data object OnButtonClick : ResetPasswordAction
    data object RequestVerificationLink : ResetPasswordAction
    data object OnDismissBottomSheet: ResetPasswordAction
    data object OnGoBackToLogin: ResetPasswordAction
}
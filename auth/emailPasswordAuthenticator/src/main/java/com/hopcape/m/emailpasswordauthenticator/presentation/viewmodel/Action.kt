package com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel

import com.hopcape.m.common.viewmodel.UserAction

sealed interface Action: UserAction {
    data class OnEmailChange(val text: String): Action
    data class OnPasswordChange(val text: String): Action
    data object Login: Action
    data object ResetPassword: Action
    data object OnGoogleSignInClick: Action
    data object OnFacebookSignInClick: Action
    data object OnDismissBottomSheet: Action
    data object OnResendVerificationEmail: Action

}
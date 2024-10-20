package com.hopcape.m.emailpasswordauthenticator.presentation.ui.register

import com.hopcape.m.common.viewmodel.UserAction

sealed interface RegisterScreenAction: UserAction {
    data class OnFullNameChange(val value: String): RegisterScreenAction
    data class OnEmailChange(val value: String): RegisterScreenAction
    data class OnPasswordChange(val value: String): RegisterScreenAction
    data class OnConfirmPasswordChange(val value: String): RegisterScreenAction
    data object OnRegisterClick: RegisterScreenAction
    data object OnLogin: RegisterScreenAction
}
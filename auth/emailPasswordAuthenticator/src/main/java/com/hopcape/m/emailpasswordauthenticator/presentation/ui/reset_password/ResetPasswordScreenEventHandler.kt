package com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavHostController
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.BaseScreenEventHandler

class ResetPasswordScreenEventHandler(
    private val navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
): BaseScreenEventHandler<ResetPasswordEvent>(snackbarHostState,navHostController) {
    override suspend fun handleEvent(event: ResetPasswordEvent) {
        when(event){
            is ResetPasswordEvent.Error -> showSnackbar(event.message)
            is ResetPasswordEvent.NavigateBack -> navigateUp()
            ResetPasswordEvent.Success -> {}
        }
    }
}
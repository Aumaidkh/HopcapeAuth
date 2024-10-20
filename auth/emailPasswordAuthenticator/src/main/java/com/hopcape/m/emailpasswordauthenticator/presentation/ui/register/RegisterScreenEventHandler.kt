package com.hopcape.m.emailpasswordauthenticator.presentation.ui.register

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavHostController
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.BaseScreenEventHandler

class RegisterScreenEventHandler(
    snackbarHostState: SnackbarHostState,
    navHostController: NavHostController
): BaseScreenEventHandler<RegisterScreenEvent>(snackbarHostState,navHostController){
    override suspend fun handleEvent(event: RegisterScreenEvent) {
        when(event){
            is RegisterScreenEvent.NavigateToLogin -> navigateUp()
            is RegisterScreenEvent.Error -> showSnackbar(event.message)
        }
    }
}
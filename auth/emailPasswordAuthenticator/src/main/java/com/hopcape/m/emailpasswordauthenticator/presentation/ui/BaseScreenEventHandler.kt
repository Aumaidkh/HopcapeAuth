package com.hopcape.m.emailpasswordauthenticator.presentation.ui

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavHostController
import com.hopcape.m.common.navigation.AppDestinations
import com.hopcape.m.common.utils.ScreenEventHandler
import com.hopcape.m.common.viewmodel.ScreenEvent

abstract class BaseScreenEventHandler<E : ScreenEvent>(
    private val snackbarHostState: SnackbarHostState,
    private val navController: NavHostController
) : ScreenEventHandler<E> {

    protected suspend fun showSnackbar(message: String) {
        snackbarHostState.showSnackbar(message)
    }

    protected fun navigateUp() {
        navController.navigateUp()
    }

    protected fun navigate(destinations: AppDestinations) {
        navController.navigate(destinations.route)
    }
}
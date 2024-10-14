package com.hopcape.m.emailpasswordauthenticator.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hopcape.m.common.navigation.AppDestinations
import com.hopcape.m.designsystem.components.sheets.AuthBottomSheet
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.ResetPassword
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.ResetPasswordAction
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.ResetPasswordEvent
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.ResetPasswordScreenViewModel
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.signin.EmailPasswordAuthScreen
import com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel.EmailPasswordAuthenticatorViewModel
import com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel.ViewEvent

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.emailPasswordAuthNavigation(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
) {
    composable(
        route = AppDestinations.LandingScreen.route
    ) {
        val context = LocalContext.current
        val bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
        val scrollState = rememberScrollState()
        val emailPasswordViewModel = hiltViewModel<EmailPasswordAuthenticatorViewModel>()
        val emailPasswordFormState by emailPasswordViewModel.state.collectAsState()

        val event by emailPasswordViewModel.event.collectAsState(initial = null)
        event?.let { viewEvent ->
            LaunchedEffect(key1 = viewEvent) {
                when (viewEvent) {
                    is ViewEvent.Success -> {
                        Toast.makeText(
                            context,
                            "Logged in...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is ViewEvent.Error -> {
                        snackbarHostState.showSnackbar(viewEvent.message)
                    }

                    is ViewEvent.Navigate -> {
                        navHostController.navigate(viewEvent.route.route)
                    }
                }
            }
        }
        EmailPasswordAuthScreen(
            uiState = emailPasswordFormState,
            onAction = emailPasswordViewModel::onAction,
            bottomSheetState = bottomSheetState,
            scrollState = scrollState
        )
    }

    composable(
        route = AppDestinations.ForgotPasswordScreen.route
    ) {
        val bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
        val resetPasswordScreenViewModel = hiltViewModel<ResetPasswordScreenViewModel>()
        val state by resetPasswordScreenViewModel.state.collectAsState()

        val event by resetPasswordScreenViewModel.event.collectAsState(initial = null)
        event?.let { viewEvent ->
            LaunchedEffect(key1 = viewEvent) {
                when (viewEvent) {
                    is ResetPasswordEvent.Navigate -> {
                        if (viewEvent.goBack) {
                            navHostController.navigateUp()
                        }
                    }

                    is ResetPasswordEvent.Error -> {
                        snackbarHostState.showSnackbar(viewEvent.message)
                    }

                    else -> {}
                }
            }
        }
        ResetPassword(
            state = state,
            onAction = resetPasswordScreenViewModel::onAction,
            sheetState = bottomSheetState
        )
    }
}
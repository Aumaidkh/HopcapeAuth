package com.hopcape.m.emailpasswordauthenticator.presentation.ui

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
import com.hopcape.m.common.utils.ScreenEventHandler
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.register.RegisterScreen
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.register.RegisterScreenEventHandler
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.register.RegisterScreenViewModel
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.ResetPassword
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.viewmodel.ResetPasswordEvent
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.viewmodel.ResetPasswordScreenEventHandler
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.viewmodel.ResetPasswordScreenViewModel
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.signin.EmailPasswordAuthScreen
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.signin.EmailPasswordScreenEventHandler
import com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel.EmailPasswordAuthenticatorViewModel
import com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel.EmailPasswordScreenEvent

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.emailPasswordAuthNavigation(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
) {
    composable(
        route = AppDestinations.LandingScreen.route
    ) {
        val context = LocalContext.current
        val eventHandler: ScreenEventHandler<EmailPasswordScreenEvent> = EmailPasswordScreenEventHandler(snackbarHostState, navHostController, context)
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
                eventHandler.handleEvent(viewEvent)
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
        val eventHandler: ScreenEventHandler<ResetPasswordEvent> = ResetPasswordScreenEventHandler(navHostController, snackbarHostState)
        val bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
        val resetPasswordScreenViewModel = hiltViewModel<ResetPasswordScreenViewModel>()
        val state by resetPasswordScreenViewModel.state.collectAsState()

        val event by resetPasswordScreenViewModel.event.collectAsState(initial = null)
        event?.let { viewEvent ->
            LaunchedEffect(key1 = viewEvent) {
                eventHandler.handleEvent(viewEvent)
            }
        }
        ResetPassword(
            state = state,
            onAction = resetPasswordScreenViewModel::onAction,
            sheetState = bottomSheetState
        )
    }
    composable(
        route = AppDestinations.RegisterScreen.route
    ){
        val viewModel = hiltViewModel<RegisterScreenViewModel>()
        val eventHandler = RegisterScreenEventHandler(snackbarHostState, navHostController)
        val state by viewModel.state.collectAsState()
        val event by viewModel.event.collectAsState(initial = null)
        val scrollState = rememberScrollState()
        val bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
        event?.let {
            LaunchedEffect(key1  = it){
                eventHandler.handleEvent(it)
            }
        }
        RegisterScreen(
            state = state,
            scrollState = scrollState,
            onAction = viewModel::onAction,
            sheetState = bottomSheetState
        )
    }
}


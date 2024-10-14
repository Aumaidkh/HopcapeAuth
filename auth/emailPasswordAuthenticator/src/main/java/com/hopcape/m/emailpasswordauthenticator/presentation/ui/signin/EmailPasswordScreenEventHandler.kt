package com.hopcape.m.emailpasswordauthenticator.presentation.ui.signin

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavHostController
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.BaseScreenEventHandler
import com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel.EmailPasswordScreenEvent

class EmailPasswordScreenEventHandler(
    snackbarHostState: SnackbarHostState,
    navHostController: NavHostController,
    private val context: Context
): BaseScreenEventHandler<EmailPasswordScreenEvent>(snackbarHostState,navHostController){
    override suspend fun handleEvent(event: EmailPasswordScreenEvent) {
        when (event) {
            is EmailPasswordScreenEvent.Success -> {
                Toast.makeText(
                    context,
                    "Logged in...",
                    Toast.LENGTH_SHORT
                ).show()
            }

            is EmailPasswordScreenEvent.Error -> showSnackbar(event.message)

            is EmailPasswordScreenEvent.Navigate -> navigate(event.route)
        }
    }
}
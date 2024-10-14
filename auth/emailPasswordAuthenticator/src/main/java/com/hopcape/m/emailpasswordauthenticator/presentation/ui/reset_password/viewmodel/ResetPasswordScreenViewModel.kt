package com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.viewmodel

import androidx.lifecycle.viewModelScope
import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.error.ErrorHandler
import com.hopcape.m.common.viewmodel.TypedViewModel
import com.hopcape.m.designsystem.components.buttons.ButtonState
import com.hopcape.m.designsystem.components.fields.input_fields.TextFieldState
import com.hopcape.m.designsystem.components.sheets.BottomSheetState
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.ResendPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ResetPasswordScreenViewModel @Inject constructor(
    private val resendPassword: ResendPassword,
    private val errorHandler: dagger.Lazy<ErrorHandler>,
) : TypedViewModel<ResetPasswordState, ResetPasswordEvent, ResetPasswordAction>() {

    private val _state = MutableStateFlow(ResetPasswordState())
    val state get() = _state.asStateFlow()

    override fun onAction(action: ResetPasswordAction) {
        when (action) {
            is ResetPasswordAction.OnEmailChange -> _state.update { it.copy(emailField = TextFieldState(action.email)) }
            ResetPasswordAction.OnButtonClick -> handleButtonClick(_state.value.email)
            ResetPasswordAction.RequestVerificationLink -> handleRequestVerificationLink(_state.value.email)
            ResetPasswordAction.OnDismissBottomSheet -> _state.update { it.copy(bottomSheetState = null) }
            ResetPasswordAction.OnGoBackToLogin -> pushEvent(ResetPasswordEvent.NavigateBack)
        }
    }

    private fun handleButtonClick(email: Email) {
        handleRequestVerificationLink(email)
    }

    private fun handleRequestVerificationLink(email: Email) {
        resendPassword.invoke(email).handleUseCase(
            onLoading = {
                _state.setLoadingOnButton(
                    loading = it,
                    loadingText = "Requesting...",
                    text = "Request Email"
                )
            },
            onSuccess = {
                _state.update {
                    it.copy(
                        bottomSheetState = BottomSheetState(
                            title = "Verification Email Sent",
                            primaryButtonState = ButtonState(text = "Login"),
                            description = "Aa email has been sent to ${_state.value.email.value} with the password reset link. Please update the password and login."
                        )
                    )
                }
            },
            onError = { pushEvent(ResetPasswordEvent.Error(errorHandler.get().resolveError(it))) }
        ).launchIn(viewModelScope)
    }

    private fun MutableStateFlow<ResetPasswordState>.setLoadingOnButton(
        loading: Boolean,
        loadingText: String,
        text: String
    ) {
        this.update {
            it.copy(
                buttonState = ButtonState(text = if (loading) loadingText else text)
            )
        }
    }
}


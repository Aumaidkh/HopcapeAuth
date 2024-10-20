package com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.hopcape.m.common.Error
import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.datatypes.Password
import com.hopcape.m.common.error.DataError
import com.hopcape.m.common.error.DomainError
import com.hopcape.m.common.error.ErrorHandler
import com.hopcape.m.common.navigation.AppDestinations
import com.hopcape.m.common.viewmodel.TypedViewModel
import com.hopcape.m.common.wrappers.UseCaseResult
import com.hopcape.m.designsystem.components.buttons.ButtonState
import com.hopcape.m.designsystem.components.fields.input_fields.TextFieldState
import com.hopcape.m.designsystem.components.sheets.BottomSheetState
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.SignInUser
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation.EmailValidator
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EmailPasswordAuthenticatorViewModel @Inject constructor(
    private val emailValidator: dagger.Lazy<EmailValidator>,
    private val passwordValidator: dagger.Lazy<PasswordValidator>,
    private val errorHandler: dagger.Lazy<ErrorHandler>,
    private val signInUser: SignInUser
): TypedViewModel<ViewState,EmailPasswordScreenEvent,Action>() {

    private val _state = MutableStateFlow(ViewState())
    val state get() = _state.asStateFlow()

    override fun onAction(action: Action){
        when(action){
            Action.Login -> validateAndLogin()
            is Action.OnEmailChange -> updateEmail(action.text)
            Action.OnFacebookSignInClick -> {}
            Action.OnGoogleSignInClick -> {}
            is Action.OnPasswordChange -> updatePassword(action.text)
            Action.ResetPassword -> pushEvent(EmailPasswordScreenEvent.Navigate(AppDestinations.ForgotPasswordScreen))
            Action.OnResendVerificationEmail -> {}
            Action.OnDismissBottomSheet -> _state.update { it.copy(bottomSheetState = null) }
            Action.OnRegisterClick -> pushEvent(EmailPasswordScreenEvent.Navigate(AppDestinations.RegisterScreen))
        }
    }

    private fun updateEmail(email: String){
        _state.update {
            it.copy(
                email = TextFieldState(email)
            )
        }
    }

    private fun updatePassword(password: String){
        _state.update {
            it.copy(
                password = TextFieldState(password)
            )
        }
    }

    private fun validateAndLogin(){
        validate {
            login()
        }
    }

    private fun login() {
        signInUser.invoke(
            _state.email(),
            _state.password()
        ).handleUseCase(
            onSuccess = { pushEvent(EmailPasswordScreenEvent.Success) },
            onLoading = { loading -> _state.update { it.copy(buttonState = ButtonState(loading = loading, text = if (loading) "Logging in..." else "Login")) }},
            onError = { handleError(it) }
        ).launchIn(viewModelScope)
    }

    private fun handleError(error: Error){
        if (error.emailNotVerified()){
            _state.showEmailNotVerifiedBottomSheet()
            return
        }
        pushEvent(EmailPasswordScreenEvent.Error(errorHandler.get().resolveError(error)))
    }

    private inline fun validate(doAfterValidation: () -> Unit){
        val emailValidationResult =
            emailValidator.get().invoke(_state.email()).also { validationResult ->
                val errorMessage = when(validationResult.error){
                    DomainError.EMTPY_EMAIL -> "Email can't be empty"
                    DomainError.INVALID_EMAIL -> "Invalid email"
                    DomainError.BLANK_EMAIL -> "Email can't be blank"
                    else -> null
                }
                _state.update {
                    it.copy(
                        email = it.email.copy(error = errorMessage)
                    )
                }
            }

        val passwordValidatorResult =
            passwordValidator.get().invoke(_state.password()).also {
                    validationResult ->
                val errorMessage = when(validationResult.error){
                    DomainError.INVALID_PASSWORD -> "Invalid Password"
                    DomainError.EMPTY_PASSWORD -> "Password can't be emtpy"
                    DomainError.PASSWORD_TOO_SHORT -> "Password too short"
                    else -> null
                }
                _state.update {
                    it.copy(
                        password = it.password.copy(error = errorMessage)
                    )
                }
            }

        val hasError = listOf(emailValidationResult,passwordValidatorResult).any {
            it is UseCaseResult.Error
        }
        if (hasError){
            return
        }
        doAfterValidation()
    }

    private fun MutableStateFlow<ViewState>.email(): Email {
        return Email(this.value.email.value)
    }

    private fun MutableStateFlow<ViewState>.password(): Password {
        return Password(this.value.password.value)
    }

    private fun Error.emailNotVerified(): Boolean {
        return this == DataError.EMAIL_NOT_VERIFIED
    }

    private fun MutableStateFlow<ViewState>.showEmailNotVerifiedBottomSheet(){
        this.update {
            it.copy(
                bottomSheetState = BottomSheetState(
                    title = "Verify Email",
                    description = "An email has already been sent to your email address. Please verify",
                    primaryButtonState = ButtonState(
                        enabled = true,
                        loading = false,
                        text = "Dismiss"
                    ),
                    secondaryButtonState = ButtonState(
                        enabled = true,
                        loading = false,
                        text = "Resend Email"
                    )
                )
            )
        }
    }

}
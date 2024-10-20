package com.hopcape.m.emailpasswordauthenticator.presentation.ui.register

import androidx.lifecycle.viewModelScope
import com.hopcape.m.common.Error
import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.datatypes.FullName
import com.hopcape.m.common.datatypes.Password
import com.hopcape.m.common.error.DomainError
import com.hopcape.m.common.error.ErrorHandler
import com.hopcape.m.common.viewmodel.TypedViewModel
import com.hopcape.m.common.wrappers.UseCaseResult
import com.hopcape.m.designsystem.components.buttons.ButtonState
import com.hopcape.m.designsystem.components.fields.input_fields.TextFieldState
import com.hopcape.m.designsystem.components.sheets.BottomSheetState
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.RegisterUser
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation.EmailValidator
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val emailValidator: dagger.Lazy<EmailValidator>,
    private val passwordValidator: dagger.Lazy<PasswordValidator>,
    private val register: dagger.Lazy<RegisterUser>,
    private val errorHandler: ErrorHandler
) : TypedViewModel<RegisterScreenState, RegisterScreenEvent, RegisterScreenAction>() {

    private val _state = MutableStateFlow(RegisterScreenState())
    val state = _state.asStateFlow()
    override fun onAction(action: RegisterScreenAction) {
        when (action) {
            is RegisterScreenAction.OnEmailChange -> _state.updateEmail(action.value)
            is RegisterScreenAction.OnFullNameChange -> _state.updateFullName(action.value)
            is RegisterScreenAction.OnPasswordChange -> _state.updatePassword(action.value)
            is RegisterScreenAction.OnConfirmPasswordChange -> _state.updateConfirmPassword(action.value)
            RegisterScreenAction.OnLogin -> handleLoginClick()
            RegisterScreenAction.OnRegisterClick -> startRegistration()
        }
    }

    private fun startRegistration() {
        validateAndRegister {
            register(
                email = _state.email(),
                password = _state.password(),
                fullName = _state.fullName()
            )
        }
    }

    private fun register(
        fullName: FullName,
        email: Email,
        password: Password
    ) {
        register.get().invoke(
            email = email,
            fullName = fullName,
            password = password
        ).handleUseCase(
            onLoading = { loading -> _state.showRegisterButtonAsLoading(loading) },
            onSuccess = { handleSuccess() },
            onError = { error -> handleError(error) }
        ).launchIn(viewModelScope)
    }

    private fun handleLoginClick(){
        _state.showBottomSheet(null)
        pushEvent(RegisterScreenEvent.NavigateToLogin)
    }
    private inline fun validateAndRegister(doAfterValidation: () -> Unit) {
        val emailValidationResult =
            emailValidator.get().invoke(_state.email()).also { validationResult ->
                val errorMessage = when (validationResult.error) {
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
            passwordValidator.get().invoke(_state.password()).also { validationResult ->
                val errorMessage = when (validationResult.error) {
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

        val confirmValidatorResult =
            passwordValidator.get().invoke(_state.confirmPassword()).also { validationResult ->
                val errorMessage = when (validationResult.error) {
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
        val passwordsMatch =
            _state.password() == _state.confirmPassword()

        if (!passwordsMatch){
            _state.update {
                it.copy(
                    password = it.password.copy(error = "Passwords don't match"),
                    confirmPassword = it.confirmPassword.copy(error = "Passwords don't match")
                )
            }
            return
        }

        val hasError = listOf(
            emailValidationResult,
            passwordValidatorResult,
            confirmValidatorResult
        ).any {
            it is UseCaseResult.Error
        }
        if (hasError) {
            return
        }
        doAfterValidation()
    }
    private fun handleSuccess() {
        _state.showBottomSheet(
            bottomSheetState = BottomSheetState(
                title = "Verify Email",
                description = "A verification email has been sent to ${_state.email().value}. Please verify the email to complete the registration",
                primaryButtonState = ButtonState(text = "Login")
            )
        )
    }
    private fun handleError(error: Error) {
        pushEvent(RegisterScreenEvent.Error(errorHandler.resolveError(error)))
    }

    private fun MutableStateFlow<RegisterScreenState>.showRegisterButtonAsLoading(loading: Boolean = false) {
        this.update { it.copy(buttonState = it.getButtonState(loading)) }
    }

    /**
     * State Updaters
     * */
    private fun MutableStateFlow<RegisterScreenState>.updateEmail(email: String){
        this.update { it.copy(email = TextFieldState(email)) }
    }
    private fun MutableStateFlow<RegisterScreenState>.updateFullName(name: String){
        this.update { it.copy(fullName = TextFieldState(name)) }
    }
    private fun MutableStateFlow<RegisterScreenState>.updatePassword(password: String){
        this.update { it.copy(password = TextFieldState(password)) }
    }
    private fun MutableStateFlow<RegisterScreenState>.updateConfirmPassword(password: String){
        this.update { it.copy(confirmPassword = TextFieldState(password)) }
    }

    /**
     * Helper Function for reading values from state
     * */
    private fun MutableStateFlow<RegisterScreenState>.email(): Email {
        return Email(this.value.email.value)
    }

    private fun MutableStateFlow<RegisterScreenState>.password(): Password {
        return Password(this.value.password.value)
    }

    private fun MutableStateFlow<RegisterScreenState>.confirmPassword(): Password {
        return Password(this.value.confirmPassword.value)
    }

    private fun MutableStateFlow<RegisterScreenState>.fullName(): FullName {
        return FullName(this.value.fullName.value)
    }

    private fun MutableStateFlow<RegisterScreenState>.showBottomSheet(bottomSheetState: BottomSheetState?) {
        this.update { state ->
            state.copy(
                bottomSheetState = bottomSheetState
            )
        }
    }
}
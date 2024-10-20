package com.hopcape.m.emailpasswordauthenticator.presentation.ui.register

import com.hopcape.m.common.viewmodel.ScreenState
import com.hopcape.m.designsystem.components.buttons.ButtonState
import com.hopcape.m.designsystem.components.fields.input_fields.TextFieldState
import com.hopcape.m.designsystem.components.sheets.BottomSheetState

data class RegisterScreenState(
    val email: TextFieldState = TextFieldState(value = ""),
    val password: TextFieldState = TextFieldState(value = ""),
    val confirmPassword: TextFieldState = TextFieldState(value = ""),
    val fullName: TextFieldState = TextFieldState(value = ""),
    val buttonState: ButtonState = ButtonState(text = "Register"),
    val bottomSheetState: BottomSheetState? = null
) : ScreenState {

    fun getButtonState(loading: Boolean): ButtonState {
        return ButtonState(
            enabled = !loading,
            loading = loading,
            text = if (loading) "Registering..." else "Register"
        )
    }
}

package com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.viewmodel

import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.viewmodel.ScreenState
import com.hopcape.m.designsystem.components.buttons.ButtonState
import com.hopcape.m.designsystem.components.fields.input_fields.TextFieldState
import com.hopcape.m.designsystem.components.sheets.BottomSheetState

data class ResetPasswordState(
    val emailField: TextFieldState = TextFieldState(""),
    val buttonState: ButtonState = ButtonState(text = "Request Email"),
    val bottomSheetState: BottomSheetState? = null
):ScreenState {
    val email: Email get() = Email(emailField.value)
}

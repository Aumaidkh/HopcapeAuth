package com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel

import com.hopcape.m.common.viewmodel.ScreenState
import com.hopcape.m.designsystem.components.buttons.ButtonState
import com.hopcape.m.designsystem.components.fields.input_fields.TextFieldState
import com.hopcape.m.designsystem.components.sheets.BottomSheetState


data class ViewState(
    val loading: Boolean = false,
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val buttonState: ButtonState = ButtonState(text = "Login"),
    val bottomSheetState: BottomSheetState? = null
): ScreenState
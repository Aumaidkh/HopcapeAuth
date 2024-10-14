package com.hopcape.m.phonebasedauthenticator.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.R
import com.hopcape.m.designsystem.components.buttons.ButtonState
import com.hopcape.m.designsystem.components.buttons.CustomIconButton

@Composable
fun PhoneLoginButton(
    modifier: Modifier = Modifier,
    state: ButtonState,
    onClick: () -> Unit
) {
    CustomIconButton(
        modifier = modifier,
        state = state,
        onClicked = onClick,
        icon = R.drawable.ic_call_answer
    )
}
package com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hopcape.m.designsystem.components.buttons.FilledButton
import com.hopcape.m.designsystem.components.fields.input_fields.InputField
import com.hopcape.m.designsystem.components.sheets.AuthBottomSheet
import com.hopcape.m.designsystem.screenPadding
import com.hopcape.m.emailpasswordauthenticator.R
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.viewmodel.ResetPasswordAction
import com.hopcape.m.emailpasswordauthenticator.presentation.ui.reset_password.viewmodel.ResetPasswordState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPassword(
    modifier: Modifier = Modifier,
    state: ResetPasswordState = ResetPasswordState(),
    onAction: (ResetPasswordAction) -> Unit = {},
    sheetState: SheetState,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(screenPadding)
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = com.hopcape.m.designsystem.R.drawable.app_logo),
            contentDescription = "Image"
        )
        Text(
            modifier = Modifier
                .padding(vertical = 24.dp),
            text = "Find your account",
            style = TextStyle(
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )
        )
        InputField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { onAction(ResetPasswordAction.OnEmailChange(it)) },
            startIconResId = R.drawable.ic_at,
            hint = "abc@def.com",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            state = state.emailField
        )
        Spacer(modifier = Modifier.height(18.dp))
        FilledButton(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth(),
            onClick = {
                keyboardController?.hide()
                onAction(ResetPasswordAction.OnButtonClick)
            },
            state = state.buttonState
        )
    }
    state.bottomSheetState?.let {
        AuthBottomSheet(
            state = it,
            sheetState = sheetState,
            onDismiss = { onAction(ResetPasswordAction.OnDismissBottomSheet) },
            onPrimaryButtonClick = { onAction(ResetPasswordAction.OnGoBackToLogin)}
        )
    }
}
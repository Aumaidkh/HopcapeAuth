package com.hopcape.m.emailpasswordauthenticator.presentation.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hopcape.m.designsystem.components.buttons.FilledButton
import com.hopcape.m.designsystem.components.fields.input_fields.InputField
import com.hopcape.m.designsystem.components.sheets.AuthBottomSheet
import com.hopcape.m.designsystem.screenPadding
import com.hopcape.m.emailpasswordauthenticator.R
import com.hopcape.m.emailpasswordauthenticator.presentation.components.RegisterButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    state: RegisterScreenState,
    scrollState: ScrollState,
    sheetState: SheetState,
    onAction: (RegisterScreenAction) -> Unit
){
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
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
            text = "Sign up",
            style = TextStyle(
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth(),
            text = "Or, register with email",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
            textAlign = TextAlign.Center
        )
        InputField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                onAction(RegisterScreenAction.OnFullNameChange(it))
            },
            startIconResId = R.drawable.ic_person,
            hint = "hope",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            state = state.fullName
        )
        Spacer(modifier = Modifier.height(24.dp))
        InputField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { onAction(RegisterScreenAction.OnEmailChange(it)) },
            startIconResId = R.drawable.ic_at,
            hint = "abc@def.com",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            state = state.email
        )
        Spacer(modifier = Modifier.height(24.dp))
        InputField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { onAction(RegisterScreenAction.OnPasswordChange(it)) },
            startIconResId = R.drawable.ic_key,
            hint = "password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            state = state.password
        )
        Spacer(modifier = Modifier.height(24.dp))
        InputField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { onAction(RegisterScreenAction.OnConfirmPasswordChange(it)) },
            startIconResId = R.drawable.ic_key,
            hint = "Re-enter password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            state = state.confirmPassword
        )
        FilledButton(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth(),
            onClick = { onAction(RegisterScreenAction.OnRegisterClick) },
            state = state.buttonState
        )

        RegisterButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Already have an account?",
            buttonText = "Sign in",
            onClick = { onAction(RegisterScreenAction.OnLogin) }
        )
    }

    state.bottomSheetState?.let {
        AuthBottomSheet(
            sheetState = sheetState,
            state = it,
            onPrimaryButtonClick = { onAction(RegisterScreenAction.OnLogin) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(
        scrollState = rememberScrollState(),
        state = RegisterScreenState(),
        sheetState = rememberStandardBottomSheetState(),
        onAction = {}
    )
}
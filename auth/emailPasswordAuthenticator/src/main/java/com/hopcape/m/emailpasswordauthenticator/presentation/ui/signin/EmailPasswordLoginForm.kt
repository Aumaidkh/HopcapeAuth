package com.hopcape.m.emailpasswordauthenticator.presentation.ui.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hopcape.m.designsystem.components.buttons.ButtonState
import com.hopcape.m.designsystem.components.buttons.FilledButton
import com.hopcape.m.designsystem.components.fields.input_fields.InputField
import com.hopcape.m.designsystem.components.fields.input_fields.TextFieldState
import com.hopcape.m.emailpasswordauthenticator.R
import com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel.Action
import com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel.ViewState

@Composable
fun EmailPasswordLoginForm(
    modifier: Modifier = Modifier,
    state: ViewState = ViewState(),
    onAction: (Action) -> Unit = {}
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 24.dp),
            text = "Login",
            style = TextStyle(
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        InputField(
            modifier = Modifier.fillMaxWidth(),
            state = state.email,
            onValueChange = { onAction(Action.OnEmailChange(it)) },
            startIconResId = R.drawable.ic_at,
            hint = "abc@def.com",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.height(24.dp))
        InputField(
            modifier = Modifier.fillMaxWidth(),
            state = state.password,
            onValueChange = { onAction(Action.OnPasswordChange(it)) },
            startIconResId = R.drawable.ic_key,
            hint = "*******",
            suffix = {
                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Forgot?")
                        }
                    },
                    onClick = {
                        onAction(Action.ResetPassword)
                    }
                )
            },
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        )
        FilledButton(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            onClick = { onAction(Action.Login)  },
            state = state.buttonState
        )
    }
}

@Preview
@Composable
fun EmailPasswordLoginScreenPreview(){
    MaterialTheme {
        EmailPasswordLoginForm(
            state = ViewState(
                email = TextFieldState("abc@gmail.com"),
                password = TextFieldState("","Invalid password"),
                buttonState = ButtonState(text = "Login")
            )
        )
    }
}
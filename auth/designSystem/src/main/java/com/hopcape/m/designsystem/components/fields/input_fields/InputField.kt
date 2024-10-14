package com.hopcape.m.designsystem.components.fields.input_fields

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    hint: String? = null,
    startIconResId: Int? = null,
    suffix: (@Composable () -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    maxLines: Int = 1,
    state: TextFieldState = TextFieldState("",null)
) {
    TextField(
        modifier = modifier,
        value = state.value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f),
            errorContainerColor = Color.Transparent,
            errorLeadingIconColor = MaterialTheme.colorScheme.error,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f),
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
        ),
        leadingIcon = {
            startIconResId?.let {
                Icon(
                    modifier = Modifier
                        .size(32.dp),
                    painter = painterResource(id = it),
                    contentDescription = "",
                )
            }
        },
        suffix = {
            suffix?.let {
                it()
            }
        },
        placeholder = {
            hint?.let{
                Text(text = it, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        supportingText = {
            state.error?.let{
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        },
        isError = state.error != null,
        maxLines = maxLines
    )
}
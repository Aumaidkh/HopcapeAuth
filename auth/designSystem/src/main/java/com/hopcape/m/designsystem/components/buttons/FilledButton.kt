package com.hopcape.m.designsystem.components.buttons

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    state: ButtonState = ButtonState(),
    onClick: () -> Unit,
) {
    val spacerWidth by animateDpAsState(targetValue = remember(state.loading) {
        if (state.loading){
            24.dp
        } else {
            0.dp
        }
    },
        label = ""
    )

    Button(
        modifier = modifier
            .height(55.dp),
        onClick = { onClick() },
        shape = MaterialTheme.shapes.medium,
        enabled = state.isEnabled,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = MaterialTheme.colorScheme.inversePrimary
        )
    ) {
        if (state.loading){
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = Modifier.width(spacerWidth))
        Text(
            text = state.text,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun FilledButtonPreview() {
    MaterialTheme {
        FilledButton(
            modifier = Modifier
                .fillMaxWidth(),
            state = ButtonState(
                loading = true,
                text = "Loading"
            ),
            onClick = {}
        )
    }
}
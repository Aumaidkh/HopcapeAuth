package com.hopcape.m.designsystem.components.sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hopcape.m.designsystem.components.buttons.FilledButton
import com.hopcape.m.designsystem.components.buttons.OutlinedButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthBottomSheet(
    state: BottomSheetState = BottomSheetState(),
    sheetState: SheetState = SheetState(skipPartiallyExpanded = false),
    onDismiss: () -> Unit = {},
    onPrimaryButtonClick: () -> Unit = {},
    onSecondaryButtonClick: () -> Unit = {}
) {
    ModalBottomSheet(
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
        ) {
            Text(
                text = state.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            FilledButton(
                modifier = Modifier.fillMaxWidth(),
                state = state.primaryButtonState,
                onClick = onPrimaryButtonClick
            )
            state.secondaryButtonState?.let {
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.secondaryButtonState,
                    onClick = onSecondaryButtonClick
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AuthBottomSheetPreview() {
    AuthBottomSheet()
}
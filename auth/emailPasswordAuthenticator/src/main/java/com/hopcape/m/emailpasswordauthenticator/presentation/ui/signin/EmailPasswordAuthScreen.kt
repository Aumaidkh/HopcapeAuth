package com.hopcape.m.emailpasswordauthenticator.presentation.ui.signin

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hopcape.m.designsystem.components.AppIcon
import com.hopcape.m.designsystem.components.buttons.ButtonState
import com.hopcape.m.designsystem.components.buttons.CustomIconButton
import com.hopcape.m.designsystem.components.sheets.AuthBottomSheet
import com.hopcape.m.emailpasswordauthenticator.presentation.components.RegisterButton
import com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel.Action
import com.hopcape.m.emailpasswordauthenticator.presentation.viewmodel.ViewState
import com.hopcape.m.phonebasedauthenticator.presentation.ui.PhoneLoginButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailPasswordAuthScreen(
    uiState: ViewState = ViewState(),
    bottomSheetState: SheetState,
    scrollState: ScrollState,
    onAction: (Action) -> Unit = {},
){
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppIcon(size = 300.dp)
        EmailPasswordLoginForm(
            state = uiState,
            onAction = onAction
        )
        Text(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .fillMaxWidth(),
            text = "Or, Login with...",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
            textAlign = TextAlign.Center
        )
        PhoneLoginButton(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
                .height(55.dp),
            state = ButtonState(text = "Phone Number"),
            onClick = {}
        )
        CustomIconButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            state = ButtonState(text = "Google"),
            onClicked = {}
        )
        RegisterButton(
            modifier = Modifier
                .padding(top = 30.dp),
            onClick = { onAction(Action.OnRegisterClick) }
        )
    }
    uiState.bottomSheetState?.let { state ->
        AuthBottomSheet(
            sheetState = bottomSheetState,
            state = state,
            onPrimaryButtonClick = { onAction(Action.OnDismissBottomSheet) },
            onSecondaryButtonClick = { onAction(Action.OnResendVerificationEmail) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LandingScreenPreview(){
    MaterialTheme {
        EmailPasswordAuthScreen(
            scrollState = rememberScrollState(),
            bottomSheetState = rememberStandardBottomSheetState()
        )
    }
}

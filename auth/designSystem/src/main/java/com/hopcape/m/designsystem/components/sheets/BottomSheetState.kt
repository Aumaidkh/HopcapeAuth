package com.hopcape.m.designsystem.components.sheets

import com.hopcape.m.designsystem.components.buttons.ButtonState

data class BottomSheetState(
    val primaryButtonState: ButtonState = ButtonState(),
    val title: String = "",
    val description: String = "",
    val imageResId: Int? = null,
    val secondaryButtonState: ButtonState? = null
)
package com.hopcape.m.designsystem.components.buttons

data class ButtonState(
    val enabled: Boolean = true,
    val loading: Boolean = false,
    val text: String = ""
) {
    val isEnabled get() = !loading && enabled
}
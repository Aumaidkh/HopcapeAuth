package com.hopcape.m.common.utils

import com.hopcape.m.common.viewmodel.ScreenEvent

interface ScreenEventHandler <E: ScreenEvent> {
    suspend fun handleEvent(event: E)
}
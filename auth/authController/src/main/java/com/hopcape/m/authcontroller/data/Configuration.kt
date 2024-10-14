package com.hopcape.m.authcontroller.data

import android.app.Activity

data class Configuration(
    val callingActivity: Activity,
    val destination: Class<*>
)
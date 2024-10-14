package com.hopcape.m.authcontroller.domain

import com.hopcape.m.authcontroller.data.Configuration

interface AuthController {

    fun configure(configuration: Configuration)

    fun authenticate()
}
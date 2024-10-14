package com.hopcape.m.authcontroller.domain.models

data class UserModel(
    val id: String,
    val email: String? = null,
    val phone: String? = null,
    val displayPic: String? = null
)

package com.hopcape.m.common.models

import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.datatypes.ID
import com.hopcape.m.common.datatypes.Phone
import java.net.URL

data class UserModel(
    val id: ID,
    val email: Email? = null,
    val phone: Phone? = null,
    val displayPic: URL? = null
)

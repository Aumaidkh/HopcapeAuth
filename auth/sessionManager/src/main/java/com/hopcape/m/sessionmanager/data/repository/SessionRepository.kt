package com.hopcape.m.sessionmanager.data.repository

import com.hopcape.m.sessionmanager.domain.models.UserSession

interface SessionRepository {

    suspend fun saveUserSession(session: UserSession)

    suspend fun getUserSession(): UserSession?
}
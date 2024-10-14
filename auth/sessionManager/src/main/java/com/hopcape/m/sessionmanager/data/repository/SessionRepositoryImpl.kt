package com.hopcape.m.sessionmanager.data.repository

import com.hopcape.m.sessionmanager.data.datasource.SharedPrefsSessionManager
import com.hopcape.m.sessionmanager.domain.models.UserSession
import javax.inject.Inject

internal class SessionRepositoryImpl @Inject constructor(
    private val datasource: SharedPrefsSessionManager
): SessionRepository {

    override suspend fun saveUserSession(session: UserSession) {
        datasource.saveSession(session)
    }

    override suspend fun getUserSession(): UserSession? {
        return datasource.getUserSession()
    }
}
package com.hopcape.m.sessionmanager.data.datasource

import android.content.Context
import com.hopcape.m.encryptionmanager.manager.EncryptionManager
import com.hopcape.m.jsonparser.JsonParser
import com.hopcape.m.sessionmanager.domain.models.UserSession
import javax.inject.Inject

internal class SharedPrefsSessionManager @Inject constructor(
    private val context: Context,
    private val encryptionManager: EncryptionManager,
    private val jsonParser: JsonParser
) {
    private val sharedPrefs by lazy {
        context.getSharedPreferences(
            "Session",
            Context.MODE_PRIVATE
        )
    }

    fun getUserSession(): UserSession? {

        // Retrieve Encrypted Session
        val encryptedSession = sharedPrefs.getString(
            "UserSession",
            ""
        )
        encryptedSession?.let {
            val decryptedSessionJson = encryptionManager.decrypt(it.toByteArray())
            return jsonParser.fromJson(decryptedSessionJson,UserSession::class.java)
        } ?: return null
    }

    suspend fun saveSession(session: UserSession) {
        // Serialize the session
        val sessionJson =
            jsonParser.toJson(session)

        // Encrypt Session
        val encryptedSession =
            encryptionManager.encrypt(sessionJson)

        // Save Session
        sharedPrefs.apply {
            // TODO: Save Here
        }
    }
}
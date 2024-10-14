package com.hopcape.m.authcontroller.data

import android.content.Intent
import com.hopcape.m.authcontroller.domain.AuthController
import com.hopcape.m.authcontroller.domain.models.UserModel
import com.hopcape.m.authcontroller.presentation.ui.AuthenticationLandingScreen
import com.hopcape.m.phonebasedauthenticator.data.api.FirebasePhoneVerificationApi
import com.hopcape.m.phonebasedauthenticator.data.api.PhoneVerificationConfig
import com.hopcape.m.sessionmanager.data.repository.SessionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AuthControllerImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val coroutineScope: CoroutineScope,
    private val phoneVerificationApi: FirebasePhoneVerificationApi
): AuthController {

    private var config: Configuration? = null

    override fun configure(configuration: Configuration) {
        this.config = configuration
        phoneVerificationApi.configure(
            config = PhoneVerificationConfig(
                activity = configuration.callingActivity
            )
        )
    }

    override fun authenticate() {
        coroutineScope.launch {
            if (alreadyAuthenticated()){
                config?.openDestinationScreen() ?: throw IllegalStateException("No Configuration set. Please call configure first.")
                return@launch
            }
            config?.openLandingScreen() ?: throw IllegalStateException("No Configuration set. Please call configure first.")
        }
    }

    private suspend fun alreadyAuthenticated(): Boolean {
        return sessionRepository.getUserSession() != null
    }

    private fun Configuration.openLandingScreen(){
        with(this.callingActivity){
            val destinationIntent = Intent(this,AuthenticationLandingScreen::class.java)
            startActivity(destinationIntent)
            finish()
        }
    }
    private fun Configuration.openDestinationScreen(){
        with(this.callingActivity){
            val destinationIntent = Intent(this,this@openDestinationScreen.destination)
            startActivity(destinationIntent)
            finish()
        }
    }
}
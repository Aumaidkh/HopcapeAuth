package com.hopcape.m.mobileauth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hopcape.m.authcontroller.data.Configuration
import com.hopcape.m.authcontroller.domain.AuthController
import com.hopcape.m.mobileauth.ui.theme.MobileAuthTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authController: AuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authController.configure(
            configuration = Configuration(
                callingActivity = this@MainActivity,
                destination = MainActivity::class.java
            )
        )
        authController.authenticate()
    }
}
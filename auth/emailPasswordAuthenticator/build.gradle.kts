plugins {
    id("hopcape.android.library")
    id("hopcape.hilt")
    id("hopcape.firebase")
    id("hopcape.compose")
}

android {
    namespace = "com.hopcape.m.emailpasswordauthenticator"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {
    useCommonModule()
    useDesignSystem()
    useSessionManager()
    useTraceManager()
    usePhoneBasedVerificationModule()
    navigation()
    firebaseAuth()
}


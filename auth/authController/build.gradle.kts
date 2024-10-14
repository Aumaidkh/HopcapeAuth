plugins {
    id("hopcape.android.library")
    id("hopcape.compose")
    id("hopcape.hilt")
}

android {
    namespace = "com.hopcape.m.authcontroller"

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
    usePhoneBasedVerificationModule()
    useEmailPasswordVerificationModule()
    useSessionManager()
    useDesignSystem()
    useEncryptionManager()
    useCommonModule()
    navigation()
    firebaseAuth()
}
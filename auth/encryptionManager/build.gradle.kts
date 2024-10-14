plugins {
    id("hopcape.android.library")
    id("hopcape.hilt")
}

android {
    namespace = "com.hopcape.m.encryptionmanager"
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

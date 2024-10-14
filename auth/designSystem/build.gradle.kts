plugins {
    id("hopcape.android.library")
    id("hopcape.compose")
}

android {
    namespace = "com.hopcape.m.designsystem"
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
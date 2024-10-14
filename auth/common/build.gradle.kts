plugins {
    id("hopcape.android.library")
    id("hopcape.hilt")
    id("hopcape.compose")
}

android {
    namespace = "com.hopcape.m.common"
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
    lifecycle()
    navigation()
}
plugins {
    id("hopcape.android.library")
    id("hopcape.hilt")
}

android {
    namespace = "com.hopcape.m.jsonparser"
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
    serialization()
    gson()
}
plugins {
    id("com.google.gms.google-services") version "4.3.15" apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.hiltAgp)
        classpath(Dependencies.javaPoet)
        classpath(Dependencies.Kotlin.gradlePlugin)
    }
}

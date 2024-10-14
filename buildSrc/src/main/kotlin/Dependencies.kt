import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeMaterial = "androidx.compose.material3:material3"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeRuntime = "androidx.compose.runtime:runtime"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
    const val javaPoet = "com.squareup:javapoet:${Versions.javaPoet}"

    object ImageLoading {
        const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object Google {
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
    }

    object Lifecycle {
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
        const val savedStateHandle = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    }

    object Navigation {
        const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigation}"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val auth = "com.google.firebase:firebase-auth"
        const val playServices = "com.google.android.gms:play-services-auth:${Versions.playServicesVersion}"
    }

    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val xSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.xSerialization}"
    }

    object Modules {
        const val authController = ":auth:authController"
        const val common = ":auth:common"
        const val designSystem = ":auth:designSystem"
        const val emailPasswordAuthenticator = ":auth:emailPasswordAuthenticator"
        const val encryptionManager = ":auth:encryptionManager"
        const val jsonParser = ":auth:jsonParser"
        const val phoneBasedAuthenticator = ":auth:phoneBasedAuthenticator"
        const val sessionManager = ":auth:sessionManager"
        const val traceManager = ":auth:traceManager"
    }
}

abstract class Feature(name: String){
    val api = ":$name:api"
    val impl = ":$name:impl"
    val di = ":$name:di"
}

// Dependencies
fun DependencyHandler.serialization(){
    implementation(Dependencies.Kotlin.xSerialization)
}

fun DependencyHandler.splashScreen() {
    implementation(Dependencies.splashScreen)
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshiConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
}

fun DependencyHandler.coil(){
    implementation(Dependencies.ImageLoading.coil)
}

fun DependencyHandler.compose() {
    lifecycle()
    coil()
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeMaterial)
    debugImplementation(Dependencies.composeUiToolingPreview)
}

fun DependencyHandler.gson(){
    implementation(Dependencies.Google.gson)
}

fun DependencyHandler.lifecycle(){
    implementation(Dependencies.Lifecycle.viewModelKtx)
    implementation(Dependencies.Lifecycle.savedStateHandle)
    implementation(Dependencies.Lifecycle.viewModelCompose)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.navigation(){
    implementation(Dependencies.Navigation.navigationCompose)
    implementation(Dependencies.Navigation.hiltNavigation)
}

fun DependencyHandler.useAuthControllerModule(){
    implementation(project(Dependencies.Modules.authController))
}

fun DependencyHandler.useCommonModule(){
    implementation(project(Dependencies.Modules.common))
}

fun DependencyHandler.useEncryptionManager(){
    implementation(project(Dependencies.Modules.encryptionManager))
}

fun DependencyHandler.useJsonParser(){
    implementation(project(Dependencies.Modules.jsonParser))
}

fun DependencyHandler.usePhoneBasedVerificationModule(){
    implementation(project(Dependencies.Modules.phoneBasedAuthenticator))
}

fun DependencyHandler.useEmailPasswordVerificationModule(){
    implementation(project(Dependencies.Modules.emailPasswordAuthenticator))
}

fun DependencyHandler.useSessionManager(){
    implementation(project(Dependencies.Modules.sessionManager))
}

fun DependencyHandler.useTraceManager(){
    implementation(project(Dependencies.Modules.traceManager))
}

fun DependencyHandler.useDesignSystem(){
    implementation(project(Dependencies.Modules.designSystem))
}

fun DependencyHandler.firebase(){
    implementation(platform(Dependencies.Firebase.bom))
    implementation(Dependencies.Firebase.playServices)
}

fun DependencyHandler.firebaseAuth(){
    implementation(Dependencies.Firebase.auth)
}
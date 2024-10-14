package features

import Feature
import implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

enum class AppFeature(val feature: Feature) {
    AUTHENTICATION(object : Feature("authentication") {}),
    ONBOARDING(object : Feature("onBoarding") {})
}

fun DependencyHandler.addFeatureApi(
    feature: AppFeature,
) {
    implementation(project(feature.feature.api))
}

fun DependencyHandler.addFeatureDi(
    feature: AppFeature
){
    implementation(project(feature.feature.di))
}

fun DependencyHandler.addCompleteFeature(
    feature: AppFeature
){
    implementation(project(feature.feature.api))
    implementation(project(feature.feature.impl))
}
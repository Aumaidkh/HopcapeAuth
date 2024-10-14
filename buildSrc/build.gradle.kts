import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    implementation("com.android.tools.build:gradle:8.2.2")
    implementation("com.squareup:javapoet:1.13.0")
}


gradlePlugin{
    plugins{
        register("androidLibrary"){
            id = "hopcape.android.library"
            implementationClass="convention.AndroidLibraryPlugin"
        }
        register("hiltPlugin"){
            id= "hopcape.hilt"
            implementationClass="convention.HiltPlugin"
        }

        register("composePlugin"){
            id="hopcape.compose"
            implementationClass="convention.ComposePlugin"
        }

        register("firebasePlugin"){
            id="hopcape.firebase"
            implementationClass="convention.FirebasePlugin"
        }
    }
}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}
package convention

import implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FirebasePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.google.gms.google-services")
            }
            dependencies {
                implementation(platform(Dependencies.Firebase.bom))
                implementation(Dependencies.Firebase.playServices)
            }
        }
    }
}
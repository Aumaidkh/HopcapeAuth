package convention

import hilt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("dagger.hilt.android.plugin")
                apply("kotlin-kapt")
            }
            dependencies {
                hilt()
            }
        }
    }

    companion object {
        const val id = "hopcape.auth.hilt"
    }
}
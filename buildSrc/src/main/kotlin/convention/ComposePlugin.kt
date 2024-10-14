package convention

import com.android.build.gradle.LibraryExtension
import compose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.getByType(LibraryExtension::class.java).apply {
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = Versions.composeCompiler
            }
        }
        target.dependencies {
            compose()
        }

    }
}
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MobileAuth"
include(":app")
include(":auth:authController")
include(":auth:encryptionManager")
include(":auth:sessionManager")
include(":auth:jsonParser")
include(":auth:common")
include(":auth:emailPasswordAuthenticator")
include(":auth:phoneBasedAuthenticator")
include(":auth:designSystem")
include(":auth:traceManager")

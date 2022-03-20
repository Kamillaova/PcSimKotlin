pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }

    plugins {
        val agpVersion = "7.1.2"
        val kotlinVersion = "1.6.10"

        kotlin("android") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion

        id("com.android.library") version agpVersion
        id("com.android.application") version agpVersion
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

rootProject.name = "PcSimKotlin"

include(":app")

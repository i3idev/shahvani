pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        // Iran mirror
        maven {
            url = uri("https://maven.myket.ir")
        }

        // Aliyun mirrors
        maven {
            url = uri("https://maven.aliyun.com/repository/gradle-plugin")
        }

        maven {
            url = uri("https://maven.aliyun.com/repository/public")
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        google()
        mavenCentral()

        // Iran mirror
        maven {
            url = uri("https://maven.myket.ir")
        }

        // Aliyun mirrors
        maven {
            url = uri("https://maven.aliyun.com/repository/google")
        }

        maven {
            url = uri("https://maven.aliyun.com/repository/public")
        }
    }
}

rootProject.name = "Shahvani"

include(":app")

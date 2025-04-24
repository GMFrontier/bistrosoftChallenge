pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Bistrosoft"
include(":app")
include(":core")
include(":feature")
include(":feature:factorial")
include(":feature:dogs")
include(":feature:events")
include(":feature:factorial:presentation")
include(":feature:dogs:presentation")
include(":feature:dogs:domain")
include(":feature:dogs:data")
include(":feature:events:presentation")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":core:presentation")
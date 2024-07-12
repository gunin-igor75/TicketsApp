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

rootProject.name = "TicketsApp"
include(":app")
include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:presentation")
include(":feature:hotel")
include(":feature:profile")
include(":feature:shortcut")
include(":feature:subscribe")
include(":core:common")
include(":core:network")
include(":core:local")
include(":feature:home:di")
include(":briddge_di_module")

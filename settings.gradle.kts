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
include(":feature:hotel")
include(":feature:profile")
include(":feature:shortcut")
include(":feature:subscribe")
include(":core:common")
include(":core:network")
include(":core:local")
include(":bridge_di_module")
include(":feature:home:di")
include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:presentation")
include(":core:di")

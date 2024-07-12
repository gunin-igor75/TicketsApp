plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:local"))
    implementation(project(":feature:home:di"))
}
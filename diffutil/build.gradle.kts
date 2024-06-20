import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.dev.bright.android.library)
    //alias(libs.plugins.publish)
    `maven-publish`
}

android {
    namespace = "pl.brightinventions.kequality.diffutil"
    
    defaultConfig {
        minSdk = 1
        compileSdk = 34
        targetSdk = 34
    }

    testOptions {
        targetSdk = 34
    }
}

dependencies {
    api(projects.kequality)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.shouldko)
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = Config.group
                artifactId = "diffutil"
                version = Config.version
            }
            register<MavenPublication>("debug") {
                from(components["debug"])
                groupId = Config.group
                artifactId = "diffutil-debug"
                version = Config.version
            }
        }
    }
}
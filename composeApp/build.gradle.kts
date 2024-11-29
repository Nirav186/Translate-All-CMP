import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.google.playServices.ads)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

//            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.7.0-alpha07")
            // Navigator
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenModel)

            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
            implementation("dev.icerock.moko:mvvm-core:0.16.1")

//            implementation("network.chaintech:cmp-preference:1.0.0")
//            implementation(libs.kotlinx.serialization.json.jvm)

            // #1 - Basic settings
            implementation("com.russhwolf:multiplatform-settings-no-arg:1.0.0")

            // #2 - For custom class serialization
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            implementation("com.russhwolf:multiplatform-settings-serialization:1.0.0")

            // #3 - For observing values as flows
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            implementation("com.russhwolf:multiplatform-settings-coroutines:1.0.0")

            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")

            implementation("network.chaintech:sdp-ssp-compose-multiplatform:1.0.4")
            implementation("network.chaintech:cmptoast:1.0.0")

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            implementation("com.squareup.retrofit2:retrofit:2.9.0")

            implementation("io.coil-kt.coil3:coil-compose:3.0.4")

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.encoding)

            api(libs.moko.permissions)
            api(libs.moko.permissions.compose)
        }
    }
}

android {
    namespace = "com.all.language.translate.speech.text"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    defaultConfig {
        applicationId = "com.all.language.translate.speech.text"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    ksp(libs.room.compiler)
}
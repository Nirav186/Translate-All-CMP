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
            linkerOpts.add("-lsqlite3")
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
//            implementation(libs.google.playServices.ads)
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
            implementation(libs.mvvm.core)

//            implementation("network.chaintech:cmp-preference:1.0.0")
//            implementation(libs.kotlinx.serialization.json.jvm)

            // #1 - Basic settings
            implementation(libs.multiplatform.settings.no.arg)

            // #2 - For custom class serialization
            implementation(libs.kotlinx.serialization.json.v141)
            implementation(libs.multiplatform.settings.serialization)

            // #3 - For observing values as flows
            implementation(libs.kotlinx.coroutines.core.v164)
            implementation(libs.multiplatform.settings.coroutines)

            implementation(libs.kotlinx.datetime)

            implementation(libs.sdp.ssp.compose.multiplatform)
            implementation(libs.cmptoast)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            implementation(libs.retrofit)

            implementation(libs.coil.compose)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.encoding)

            api(libs.moko.permissions)
            api(libs.moko.permissions.compose)

            implementation(libs.composeIcons.tablerIcons)
            implementation(libs.composeIcons.fontAwesome)
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
        versionCode = 3
        versionName = "0.0.3"
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
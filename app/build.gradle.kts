plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp") version libs.versions.ksp.get()
    kotlin("plugin.serialization") version "1.7.0"
}

android {
    namespace = "com.example.medicinetracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.medicinetracker"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "API_URL", "\"run.mocky.io/v3\"")
        }
    }

    android.buildFeatures.buildConfig = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material)

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.mockitoCore)
    testImplementation(libs.mockitoInline)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.kotlinxCoroutinesTest)
    testImplementation(libs.androidxArchCoreTesting)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation(libs.ktor.ktor.client.mock)


    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Koin
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.navigation)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)

    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.serialization.gson)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.auth)
    // Logback
    implementation(libs.logback.classic)

    //Splashscreen
    implementation(libs.core.splashscreen)

    implementation(libs.constraintlayout.compose)

    implementation(libs.accompanist.systemuicontroller)

    //Navigation
    implementation(libs.navigation.compose)

    //Coil
    implementation(libs.coil.compose)

    //Navigation
    implementation(libs.navigation.compose)

    //Compose Icons
    implementation(libs.material.icons.extended)


}
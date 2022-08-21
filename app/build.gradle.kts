plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.dreamsoftware.coins4all"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.dreamsoftware.coins4all"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
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
        kotlinCompilerExtensionVersion = "1.3.0-rc02"
    }
    packagingOptions {
        resources {
            excludes += ("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    //Android Essentials
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("com.google.android.material:material:1.7.0-beta01")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.window:window:1.0.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.3")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-android-compiler:2.42")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    implementation("androidx.lifecycle:lifecycle-service:2.5.1")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.0")

    //Compose
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.compose.ui:ui:1.3.0-alpha03")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.0-alpha03")
    implementation("androidx.compose.material3:material3:1.0.0-alpha16")
    implementation("androidx.compose.material:material:1.3.0-alpha03")
    implementation("androidx.compose.material:material-icons-core:1.2.1")
    implementation("androidx.compose.material:material-icons-extended:1.2.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha01")
    implementation("androidx.navigation:navigation-compose:2.5.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.compose.foundation:foundation:1.3.0-alpha03")

    //Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.25.0")
    implementation("com.google.accompanist:accompanist-flowlayout:0.24.5-alpha")

    //Coil
    implementation("io.coil-kt:coil:2.1.0")
    implementation("io.coil-kt:coil-compose:2.1.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //Room
    implementation("androidx.room:room-runtime:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")

}
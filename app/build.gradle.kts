plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.shahvani.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.shahvani.app"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val keystorePropertiesFile = rootProject.file("keystore.properties")
            val keystoreProperties = java.util.Properties()
            if (keystorePropertiesFile.exists()) {
                keystoreProperties.load(keystorePropertiesFile.inputStream())
            }
            val storeFile = keystoreProperties["storeFile"]?.let { java.io.File(it) }
            if (storeFile == null) {
                throw gradleException(
                    "Release signing not configured. Create 'keystore.properties' in project root with " +
                    "storeFile, storePassword, keyAlias, keyPassword. Or set STORE_FILE, STORE_PASSWORD, " +
                    "KEY_ALIAS, KEY_PASSWORD environment variables."
                )
            }
            setStoreFile(storeFile)
            setStorePassword(keystoreProperties["storePassword"] as? String ?: System.getenv("STORE_PASSWORD")
                ?: throw gradleException("STORE_PASSWORD not set"))
            setKeyAlias(keystoreProperties["keyAlias"] as? String ?: System.getenv("KEY_ALIAS")
                ?: throw gradleException("KEY_ALIAS not set"))
            setKeyPassword(keystoreProperties["keyPassword"] as? String ?: System.getenv("KEY_PASSWORD")
                ?: throw gradleException("KEY_PASSWORD not set"))
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // AppCompat (required for Material3 resources)
    implementation(libs.appcompat)
    
    // Material3 Core (provides Material3 theme resources and attributes)
    implementation(libs.material3.core)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.tooling)

    // Material3
    implementation(libs.material3)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Network
    implementation(libs.bundles.network)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // DataStore
    implementation(libs.datastore.preferences)

    // Coroutines
    implementation(libs.coroutines.android)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Paging
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    // Splash
    implementation(libs.core.splashscreen)

    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation("io.mockk:mockk:1.13.12")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

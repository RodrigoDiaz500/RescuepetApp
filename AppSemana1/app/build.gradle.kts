plugins {
    id("com.android.application")
    kotlin("android")

    // 1. Necesario para el procesador de anotaciones de Room (kapt)
    kotlin("kapt")

    // 2. SOLUCIÓN AL ERROR: Nuevo plugin requerido para Compose con Kotlin 2.0+
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.sumativa2" // Asegúrate de que tu namespace sea este
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sumativa2"
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
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
    // ESTE BLOQUE AHORA ESTÁ VACÍO porque el plugin de Compose Compiler lo maneja
    composeOptions {
        // kotlinCompilerExtensionVersion = "1.5.1" <--- ESTO YA NO ES NECESARIO Y CAUSABA EL ERROR
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

    // Navegación de Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ROOM (SQLite Persistence Library)
    val roomVersion = "2.6.1"

    // Core Room Library
    implementation("androidx.room:room-runtime:$roomVersion")
    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")
    // KAPT (Compilador de Room)
    kapt("androidx.room:room-compiler:$roomVersion")

    // Para usar los ViewModels y LiveData en Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Pruebas
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
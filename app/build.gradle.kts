plugins {

    alias(libs.plugins.kotlin.android)

    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.marketpets"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.marketpets"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation("com.google.android.material:material:1.9.0")
    implementation ("com.google.android.material:material:1.6.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation ("com.google.android.material:material:1.5.0")

    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:2.7.5")

    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))
    implementation("com.firebaseui:firebase-ui-auth:9.0.0")
    implementation ("com.google.firebase:firebase-auth-ktx:22.1.2")
    implementation ("com.google.firebase:firebase-database-ktx")
    implementation ("com.google.firebase:firebase-database:20.3.0")
    implementation ("com.google.firebase:firebase-storage-ktx:20.3.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
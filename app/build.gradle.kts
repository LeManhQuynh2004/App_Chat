plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.appchat"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appchat"
        minSdk = 30
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    // Firebase BOM for managing Firebase dependencies
    implementation (platform("com.google.firebase:firebase-bom:32.7.3"))

    // Firebase Authentication and Realtime Database
    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation ("com.google.firebase:firebase-database-ktx")

    // FirebaseUI for Authentication
    implementation ("com.firebaseui:firebase-ui-auth:8.0.2")

    // FirebaseUI for Realtime Database (if needed)
    // implementation "com.firebaseui:firebase-ui-database:8.0.2"

    // Google Play services for Authentication
    implementation ("com.google.android.gms:play-services-auth:20.5.0")
}

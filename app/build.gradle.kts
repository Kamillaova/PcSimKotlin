plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

android {

    viewBinding {
        isEnabled = true
    }

    compileSdk = 32

    defaultConfig {
        applicationId = "com.niksaen.pcsim"
        minSdk = 23
        targetSdk = 32
        versionCode = 17
        versionName = "1.0.9 Alpha (Kotlin)"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")

    implementation("com.google.code.gson:gson:2.9.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    api("com.badlogicgames.gdx:gdx-backend-android:1.10.0")
}
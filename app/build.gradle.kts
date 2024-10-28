plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.nasahacker.convertit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nasahacker.convertit"
        minSdk = 21
        targetSdk = 34
        versionCode = 5
        versionName = "1.0.4-beta"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures { viewBinding = true }
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
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

tasks.register("cleanArtifacts") {
    group = "build"
    description = "Cleans the release and build folders."

    doLast {
        delete(fileTree("$rootDir/app/release").files)
        delete(fileTree("$rootDir/app/build").files)
    }
}

// Make cleanArtifacts part of the clean task
tasks.named("clean") {
    dependsOn("cleanArtifacts")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.mobile.ffmpeg.full.gpl)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.nasacircleimageview)
}



plugins {
    alias(libs.plugins.android.application)
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile =
                file("D:\\Yandex.Disk\\JavaProjects\\AndroidStudioProjects\\KeyStore\\my_key_store.keystore")
            storePassword = "030366st"
            keyAlias = "common_key"
            keyPassword = "030366st"
        }
    }
    namespace = "com.example.numericalcodesofkryon"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.numericalcodesofkryon"
        minSdk = 24
        targetSdk = 36
        versionCode = 3
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.icu4j)
    implementation(libs.commons.lang3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
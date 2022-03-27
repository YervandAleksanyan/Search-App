plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = "com.test.androiddevtest"
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.appVersionCode
        versionName = Android.appVersionName
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures.dataBinding = true
}

dependencies {

    implementation(Deps.kotlinStdlibJdk8)

    //Gson
    implementation(Deps.gson)

    //Coroutines library
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)

    //AAC libraries
    implementation(Deps.Androidx.lifecycleEx)
    kapt(Deps.Androidx.lifeCycleCommon)
    implementation(Deps.Androidx.lifecycleViewModelKtx)
    implementation(Deps.Androidx.coreKtx)

    //support
    implementation(Deps.Androidx.appCompat)
    implementation(Deps.Androidx.material)

    //DI
    implementation(Deps.koinAndroid)
    implementation(Deps.koinCore)

    //UI
    implementation(Deps.Androidx.constraintLayout)
    implementation(Deps.Androidx.recyclerView)
    implementation(Deps.Androidx.swipeRefreshLayout)

    //Glide
    implementation(Deps.glide)
    kapt(Deps.glideKapt)

    //Room
    implementation(Deps.roomRuntime)
    implementation(Deps.roomKtx)
    kapt(Deps.roomKapt)


}
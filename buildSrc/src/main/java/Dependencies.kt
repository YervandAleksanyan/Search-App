object Versions {
    const val kotlin = "1.5.30"
}

object Android {
    const val minSdk = 23
    const val targetSdk = 32
    const val compileSdk = 32
    const val appVersionCode = 1
    const val appVersionName = "1.0.0"
}


object Deps {
    private const val coroutinesVersion = "1.3.9"
    private const val koin = "3.2.0-beta-1"
    private const val glideVersion = "4.13.1"
    private val roomVersion = "2.4.2"

    const val kotlinStdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val koinAndroid = "io.insert-koin:koin-android:${koin}"
    const val koinCore = "io.insert-koin:koin-core:${koin}"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"

    const val gson = "com.google.code.gson:gson:2.9.0"


    const val glide = "com.github.bumptech.glide:glide:${glideVersion}"
    const val glideKapt = "com.github.bumptech.glide:compiler:${glideVersion}"

    val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    val roomKapt = "androidx.room:room-compiler:$roomVersion"
    val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val timber = "com.jakewharton.timber:timber:5.0.1"
    const val bindingPropertyDelegate =
        "com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.3"

    object Androidx {
        private const val lifecycleVersion = "2.2.0"

        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val coreKtx = "androidx.core:core-ktx:1.3.2"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.0-beta01"
        const val lifecycleEx = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
        const val lifecycleViewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val lifeCycleCommon = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
        const val material = "com.google.android.material:material:1.4.0"

        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

    }

}
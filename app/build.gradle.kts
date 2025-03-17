plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.weatherreport"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weatherreport"
        minSdk = 24
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
        buildFeatures {
            viewBinding = true // 启用 ViewBinding
            dataBinding = true // 如果需要 DataBinding
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Android 核心库
    implementation(libs.appcompat)
    implementation(libs.material)
    // Room 数据库
    implementation(libs.room.common)
    implementation(libs.room.runtime)
    // Retrofit 网络请求
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.gson)
    // Glide 图片加载
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
    // Lifecycle 组件
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    // 单元测试
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    annotationProcessor(libs.room.compiler)
}
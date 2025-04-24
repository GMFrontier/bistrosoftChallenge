plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.devtools.ksp") // Use KSP instead of kapt
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.android.junit5)
}

android {
    namespace = "com.frommetoyou.bistrosoft"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.frommetoyou.bistrosoft"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.frommetoyou.bistrosoft.HiltTestRunner"
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
    buildFeatures {
        compose = true
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:factorial:presentation"))
    implementation(project(":feature:dogs:presentation"))
    implementation(project(":feature:dogs:domain"))
    implementation(project(":feature:dogs:data"))
    implementation(project(":feature:events:presentation"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    api(libs.hilt.android)
    api(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.android.compiler)

    //test

    testImplementation(libs.junit)
    testImplementation(libs.assertk)
    testImplementation(libs.mockk)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.turbine)

    androidTestImplementation(libs.assertk)
    androidTestImplementation(libs.mockkAndroid)
    androidTestImplementation(libs.coroutinesTest)
    androidTestImplementation(libs.turbine)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.espressoIntens)
    androidTestImplementation(libs.testRunner)
    androidTestImplementation(libs.rules)
    androidTestImplementation(libs.navigationTesting)
    androidTestImplementation(libs.uiautomator)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    testImplementation(libs.jupiter.api)
    testRuntimeOnly(libs.jupiter.engine)
    testImplementation(libs.jupiter.params)

    androidTestUtil(libs.orchestrator)

    androidTestImplementation(libs.workTesting)
    androidTestImplementation(libs.uiTestManifest)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
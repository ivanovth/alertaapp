
val room_version = "2.5.0"
val hilt_version = "2.48.1"
val hilt_navigation_compose_version = "1.2.0"
val retrofit_version = "2.9.0"
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.alertaurbana"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.alertaurbana"
        minSdk = 26
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
//    tasks.named("kspDebugKotlin") {
//        kotlinOptions {
//            jvmTarget = "17" // Set the same Java version as 'kspDebugKotlin'
//        }
//    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-text-google-fonts:1.6.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.runtime:runtime:1.6.6")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.6")



/// librerias room
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    ksp("androidx.room:room-compiler:$room_version")

///dagger Hilt

    implementation ("com.google.dagger:hilt-android:$hilt_version")
    kapt ("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation ("androidx.hilt:hilt-navigation-compose:$hilt_navigation_compose_version")

    // To use Kotlin Symbol Processing (KSP)
//retrofit 
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofit_version")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")


    implementation("androidx.compose.material3:material3-window-size-class:1.2.1")

    implementation ("androidx.compose.material:material-icons-extended:1.6.4")


    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("com.google.android.gms:play-services-location:21.2.0")

    implementation ("com.google.accompanist:accompanist-permissions:0.30.1")
    implementation ("io.coil-kt:coil-compose:2.2.2")

    implementation("io.coil-kt:coil:2.4.0")
   // implementation ("com.mapbox.navigation:ui-dropin:2.10.1")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.mapbox.extension:maps-compose:11.3.0")
    implementation ("com.mapbox.maps:android:11.3.0")  // Asegúrate de usar la versión correcta
    implementation ("com.mapbox.plugin:maps-animation:11.3.0")
    implementation ("com.mapbox.plugin:maps-annotation:11.3.0")
    implementation ("com.mapbox.plugin:maps-attribution:11.3.0")  // Para AttributionSettings
    implementation ("com.mapbox.plugin:maps-compass:11.3.0")  // Para CompassSettings
    implementation ("com.mapbox.plugin:maps-gestures:11.3.0")
    implementation ("com.mapbox.plugin:maps-locationcomponent:11.3.0")
    implementation ("com.mapbox.plugin:maps-logo:11.3.0")
    implementation ("com.mapbox.plugin:maps-overlay:11.3.0")
    implementation ("com.mapbox.plugin:maps-scalebar:11.3.0")
    implementation ("com.mapbox.plugin:maps-animation:11.3.0")
    implementation ("com.mapbox.extension:maps-localization:11.3.0")
    implementation ("com.google.accompanist:accompanist-pager:0.28.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.28.0")
//Map Libre
   // implementation ("org.maplibre.gl:android-sdk:10.3.1")
    //implementation ("com.mapbox.navigation:ui-dropin:2.10.1")
//    implementation ("com.mapbox.maps:android:10.11.2")
    implementation("com.google.android.gms:play-services-location:21.2.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")


}



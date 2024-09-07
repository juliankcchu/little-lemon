# little-lemon
little lemon Android app for Meta Android Developer Professional Certificate capstone project


kts gradle
- unresolved-reference-libraries-buildsrc 
  https://stackoverflow.com/questions/71505101/
- unresolved-reference-kapt-and-ksp-in-android-studio-when-trying-to-do-the-setuplibs.versions.toml
  https://stackoverflow.com/questions/78008527/ 
  add version & add libraries
  build.gradle.kts; 


- for navigation
    implementation(libs.androidx.navigation.compose)


- permission
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />


- for ktor (HTTP)
    implementation "io.ktor:ktor-client-core:2.1.3"
    implementation "io.ktor:ktor-client-android:2.1.3"
    implementation "io.ktor:ktor-client-content-negotiation:2.1.3"
    implementation "io.ktor:ktor-serialization-kotlinx-json:2.1.3"
  ref: https://github.com/mende273/KtorAndroidClientExample


- kotlin - Serializer for class '...' is not found. Mark the class as @Serializable or provide the serializer explicitly - Stack Overflow
  https://stackoverflow.com/questions/71988144/serializer-for-class-is-not-found-mark-the-class-as-serializable-or-prov

  FATAL EXCEPTION: DefaultDispatcher-worker-4
  Process: com.example.littlelemon, PID: 10624
  kotlinx.serialization.SerializationException: Serializer for class 'MenuNetwork' is not found.
  Mark the class as @Serializable or provide the serializer explicitly.


- for ROOM (SQLite)
  // @ref https://www.coursera.org/learn/working-with-data-in-android/supplement/zhOyC/room-in-detail

    plugins {
        id 'com.android.application'
        id 'org.jetbrains.kotlin.android'
        id 'org.jetbrains.kotlin.kapt'
    }
    dependencies {
        def room_version = "2.4.3"
        ...
        implementation "androidx.room:room-runtime:$room_version"
        kapt "androidx.room:room-compiler:$room_version"
    }

    dependencies {
        def room_version = "2.4.3"
        ...
        implementation "androidx.room:room-runtime:$room_version"
        annotationProcessor "androidx.room:room-compiler:$room_version"    <-- kapt

        // optional - Kotlin Extensions and Coroutines support for Room
        implementation "androidx.room:room-ktx:$room_version"

        // To support LiveData in Compose
        implementation 'androidx.compose.runtime:runtime-livedata:1.3.2'
        implementation(libs.androidx.compose.runtime.livedata)
    }
  androidx-compose-runtime-livedata = { group = "androidx.compose.runtime", name = "runtime-livedata", version.ref = "runtimeLivedata" }
  runtimeLivedata = "1.6.8"


- GlideImage
- with-glide-compose-glideimage-composable-function-how-to-enable-disk-caching
  https://stackoverflow.com/questions/77498929/
- https://bumptech.github.io/glide/javadocs/4140/integration/compose/com.bumptech.glide.integration.compose/-glide-image.html
  In the dependencies block of the app/build.gradle file add the glide compose dependency:
  implementation "com.github.bumptech.glide:compose:1.0.0-alpha.1"
  Use the GlideImage library to load images using the URL present in the menu item image attribute.
- GlideImage is experimental and is likely to change or to be removed in the future
  @OptIn(ExperimentalGlideComposeApi::class)
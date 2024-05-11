plugins {
    id("com.android.application") version "8.4.0" apply false
    id("com.android.library") version "8.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
}
buildscript {
        repositories {
            google()
            mavenCentral()
        }
        dependencies {
            classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        }

    }
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}



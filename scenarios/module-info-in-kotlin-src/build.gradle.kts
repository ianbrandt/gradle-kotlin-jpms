import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    alias(libs.plugins.kotlin.gradle.plugin)
}

group = "com.ianbrandt"
version = "1.0-SNAPSHOT"

val javaTargetVersion = JavaVersion.VERSION_17.toString()

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain  {
        languageVersion.set(JavaLanguageVersion.of(javaTargetVersion))
    }
}

application {
    mainModule.set("com.ianbrandt.jpms")
    mainClass.set("com.ianbrandt.jpms.HelloWorldKt")
}

tasks {

    withType<JavaCompile>().configureEach {
        sourceCompatibility = javaTargetVersion
        targetCompatibility = javaTargetVersion
    }

    // Per https://github.com/gradle/gradle/issues/17271, Kotlin modules only
    // compile if the Kotlin compiler destination directory is set to that of
    // Java compiler.
    named<KotlinCompile>("compileKotlin").configure {
        val compileJava = getByName<JavaCompile>("compileJava")
        destinationDirectory.set(compileJava.destinationDirectory)
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

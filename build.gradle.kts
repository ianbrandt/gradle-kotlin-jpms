import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "2.4.21"
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

    named<Wrapper>("wrapper").configure {
        gradleVersion = "9.8.2"
        distributionType = ALL
    }
}

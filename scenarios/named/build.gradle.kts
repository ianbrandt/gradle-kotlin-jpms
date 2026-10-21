import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	application
	alias(libs.plugins.kotlin.gradle.plugin)
}

group = "com.ianbrandt"
version = "1.0-SNAPSHOT"

val javaTargetVersion = JavaVersion.VERSION_21.toString()

repositories {
	mavenCentral()
}

application {
	mainModule.set("com.ianbrandt.jpms")
	mainClass.set("com.ianbrandt.jpms.HelloWorldKt")
}

tasks {

	withType<JavaCompile>().configureEach {
		with(options) {
			release = javaTargetVersion.toInt()
			isFork = true
		}
	}

	withType<KotlinCompile>().configureEach {
		compilerOptions {
			optIn.addAll(
				"kotlin.ExperimentalStdlibApi",
				"kotlin.contracts.ExperimentalContracts",
			)
			// Planned for deprecation:
			// https://youtrack.jetbrains.com/issue/KT-61035/
			freeCompilerArgs.addAll(
				// https://youtrack.jetbrains.com/issue/KT-61410/
				"-Xjsr305=strict",
				// https://youtrack.jetbrains.com/issue/KT-49746/
				"-Xjdk-release=$javaTargetVersion",
			)
		}
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

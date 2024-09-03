import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
}

val javaTargetVersion = JavaVersion.VERSION_21.toString()

dependencies {

	runtimeOnly(kotlin("reflect"))
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
	// the Java compiler.
	named<KotlinCompile>("compileKotlin").configure {
		val compileJava by getting(JavaCompile::class)
		destinationDirectory.set(compileJava.destinationDirectory)
	}
}

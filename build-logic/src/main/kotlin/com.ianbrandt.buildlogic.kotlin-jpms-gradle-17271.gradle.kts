import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.ianbrandt.buildlogic.kotlin-project")
}

tasks {

	// Per https://github.com/gradle/gradle/issues/17271, Kotlin modules only
	// compile if the Kotlin compiler destination directory is set to that of
	// the Java compiler.
	named<KotlinCompile>("compileKotlin").configure {
		val compileJava = getByName<JavaCompile>("compileJava")
		destinationDirectory.set(compileJava.destinationDirectory)
	}
}

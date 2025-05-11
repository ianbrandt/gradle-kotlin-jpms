plugins {
	application
	id("com.ianbrandt.build.kotlin-project")
}

val mainModuleName = "com.ianbrandt.jpms.demo"

application {
	mainModule.set(mainModuleName)
	mainClass.set("com.ianbrandt.jpms.HelloWorldKt")
}

tasks {
	// Enable JPMS for a module with only Kotlin sources:
	// https://youtrack.jetbrains.com/issue/KT-55389/
	named<JavaCompile>("compileJava") {
		// For Gradle configuration cache compatibility,
		// `CommandLineArgumentProvider { }` must only capture local variables
		// of compatible types...
		// https://docs.gradle.org/current/userguide/configuration_cache.html#config_cache:not_yet_implemented:accessing_top_level_at_execution
		val localMainModuleName = mainModuleName
		// https://docs.gradle.org/current/userguide/configuration_cache.html#gradle_model_types
		val mainSourceSetOutput: FileCollection = sourceSets["main"].output
		options.compilerArgumentProviders.add(CommandLineArgumentProvider {
			listOf(
				"--patch-module",
				"$localMainModuleName=${mainSourceSetOutput.asPath}"
			)
		})
	}
}

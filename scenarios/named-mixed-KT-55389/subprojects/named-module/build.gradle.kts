plugins {
	application
	id("com.ianbrandt.buildlogic.kotlin-project")
}

// The configuration cache does not currently support accessing top-level
// properties at execution time (https://github.com/gradle/gradle/issues/22879).
// Declare an object constant instead for the `CommandLineArgumentProvider`
// below.
object ModuleInfo {
	const val MODULE_NAME = "com.ianbrandt.jpms.app"
}

application {
	mainModule.set(ModuleInfo.MODULE_NAME)
	mainClass.set("com.ianbrandt.jpms.app.Main")
}

// Enable JPMS for Kotlin: https://youtrack.jetbrains.com/issue/KT-55389.
tasks {
	named<JavaCompile>("compileJava").configure {
		val mainSourceSetOutput: FileCollection = sourceSets["main"].output
		options.compilerArgumentProviders.add(CommandLineArgumentProvider {
			listOf(
				"--patch-module",
				"${ModuleInfo.MODULE_NAME}=${mainSourceSetOutput.asPath}"
			)
		})
	}
}

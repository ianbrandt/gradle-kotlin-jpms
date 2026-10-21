plugins {
	id("com.ianbrandt.buildlogic.kotlin-project")
}

// Enable JPMS for Kotlin: https://youtrack.jetbrains.com/issue/KT-55389.
tasks.named<JavaCompile>("compileJava").configure {
	val mainSourceSetOutput: FileCollection = sourceSets["main"].output
	options.compilerArgumentProviders.add(CommandLineArgumentProvider {
		val moduleName = "com.ianbrandt.jpms.lib"
		listOf(
			"--patch-module",
			"$moduleName=${mainSourceSetOutput.asPath}"
		)
	})
}

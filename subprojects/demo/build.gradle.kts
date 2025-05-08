plugins {
	application
	id("com.ianbrandt.build.kotlin-project")
}

application {
	mainModule.set("com.ianbrandt.jpms.demo")
	mainClass.set("com.ianbrandt.jpms.HelloWorld")
}

tasks {
	// Enable JPMS: https://youtrack.jetbrains.com/issue/KT-55389/
	named<JavaCompile>("compileJava") {
		val mainSourceSetOutput: FileCollection = sourceSets["main"].output
		options.compilerArgumentProviders.add(CommandLineArgumentProvider {
			listOf(
				"--patch-module",
				"com.ianbrandt.jpms.demo=${mainSourceSetOutput.asPath}"
			)
		})
	}
}

plugins {
	application
	id("com.ianbrandt.build.kotlin-project")
	id("com.ianbrandt.build.test.unit-test-suite")
}

application {
	mainModule.set("com.ianbrandt.jpms.demo")
	mainClass.set("com.ianbrandt.jpms.HelloWorld")
}

tasks {
	named<JavaCompile>("compileJava") {
		options.compilerArgumentProviders.add(CommandLineArgumentProvider {
			listOf(
				"--module-path",
				classpath.asPath,
				"--patch-module",
				"com.ianbrandt.jpms.demo=${sourceSets["main"].output.asPath}"
			)
		})
	}
}

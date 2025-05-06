plugins {
	application
	id("com.ianbrandt.buildlogic.kotlin-project")
}

val jpmsArgs = listOf(
	"--add-reads", "com.ianbrandt.jpms.named=ALL-UNNAMED",
)

application {
	mainModule.set("com.ianbrandt.jpms.named")
	mainClass.set("com.ianbrandt.jpms.HelloWorldKt")
	applicationDefaultJvmArgs = jpmsArgs
}

dependencies {
	implementation(projects.subprojects.unnamedModule)
}

tasks {
	withType<JavaCompile>().configureEach {
		options.apply {
			compilerArgs.addAll(jpmsArgs)
			isFork = true
		}
	}
}

plugins {
	application
	id("com.ianbrandt.buildlogic.kotlin-project")
	//alias(libs.plugins.gradle.idea.ext.plugin)
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

// Getting IntelliJ internal errors and Gradle sync errors trying to use the
// `idea` DSL per https://youtrack.jetbrains.com/issue/IDEA-154038:
//idea.project.settings.compiler.javac.javacAdditionalOptions =
//	jpmsArgs.joinToString(" ")

import org.jetbrains.gradle.ext.compiler
import org.jetbrains.gradle.ext.settings

plugins {
	application
	id("com.ianbrandt.buildlogic.kotlin-project")
	alias(libs.plugins.gradle.idea.ext.plugin)
}

val jpmsArgs = listOf(
	"--add-reads", "com.ianbrandt.jpms.named=ALL-UNNAMED",
)

application {
	mainModule.set("com.ianbrandt.jpms.named")
	mainClass.set("com.ianbrandt.jpms.HelloWorld")
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

// Register the JPMS compiler arguments with IntelliJ per
// https://youtrack.jetbrains.com/issue/IDEA-154038:
rootProject.idea.project.settings.compiler.javac.javacAdditionalOptions =
	jpmsArgs.joinToString(" ")

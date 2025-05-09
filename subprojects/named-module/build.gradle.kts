import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	application
	id("com.ianbrandt.buildlogic.kotlin-project")
}

val mainModuleName = "com.ianbrandt.jpms.named"

val jpmsArgs = listOf(
	"--add-reads", "$mainModuleName=ALL-UNNAMED",
)

val javaToKotlinJpmsArgMapping = mapOf(
	"--add-exports" to "-Xadd-exports",
	"--add-reads" to "-Xadd-reads",
	"--patch-module" to "-Xpatch-module",
)

val kotlinJpmsArgs = jpmsArgs.chunked(2).flatMap { (jpmsArg, value) ->
	val kotlinJpmsArg = (javaToKotlinJpmsArgMapping[jpmsArg]
		?: error("Unrecognized JPMS argument: $jpmsArg"))
	listOf(kotlinJpmsArg, value)
}

application {
	mainModule.set(mainModuleName)
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
	// Not supported yet: https://youtrack.jetbrains.com/issue/KT-20740
	//withType<KotlinCompile>().configureEach {
	//	compilerOptions {
	//		freeCompilerArgs.addAll(kotlinJpmsArgs)
	//	}
	//}
}

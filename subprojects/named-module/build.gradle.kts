plugins {
	application
	id("com.ianbrandt.build.kotlin-project")
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
	implementation(project(":subprojects:unnamed-module"))
}

tasks {
	withType<JavaCompile>().configureEach {
		options.apply {
			compilerArgs.addAll(jpmsArgs)
			isFork = true
		}
	}
}

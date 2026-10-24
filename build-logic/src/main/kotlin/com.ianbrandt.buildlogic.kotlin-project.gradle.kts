import java.util.jar.JarFile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
}

val javaTargetVersion = JavaVersion.VERSION_21.toString()

tasks {

	withType<JavaCompile>().configureEach {
		with(options) {
			release = javaTargetVersion.toInt()
			isFork = true
		}
	}

	withType<KotlinCompile>().configureEach {
		compilerOptions {
			optIn.addAll(
				"kotlin.ExperimentalStdlibApi",
				"kotlin.contracts.ExperimentalContracts",
			)
			// Planned for deprecation:
			// https://youtrack.jetbrains.com/issue/KT-61035/
			freeCompilerArgs.addAll(
				// https://youtrack.jetbrains.com/issue/KT-61410/
				"-Xjsr305=strict",
				// https://youtrack.jetbrains.com/issue/KT-49746/
				"-Xjdk-release=$javaTargetVersion",
			)
		}
	}
}

// Splits compileKotlin's dependencies the way JavaCompile does, for KT-90678:
// modules go to -Xmodule-path, and everything else stays on -classpath.
// kotlinc reads -Xmodule-path only for a named module, so the split is limited
// to projects with a module-info.java.
if (providers.gradleProperty("splitKotlinModulePath").orNull.toBoolean() &&
	file("src/main/java/module-info.java").exists()
) {
	// A lambda, since a script function would capture the script object, which
	// the configuration cache can't serialize.
	val isModule = { file: File ->
		when {
			file.isDirectory -> File(file, "module-info.class").exists()
			file.isFile && file.name.endsWith(".jar") -> JarFile(file).use { jar ->
				jar.getEntry("module-info.class") != null ||
					jar.entries().asSequence().any {
						it.name.matches(Regex("META-INF/versions/\\d+/module-info\\.class"))
					} ||
					jar.manifest?.mainAttributes?.getValue("Automatic-Module-Name") != null
			}
			else -> false
		}
	}
	tasks.named<KotlinCompile>("compileKotlin").configure {
		val dependencies = files(configurations.named("compileClasspath"))
		val modules = dependencies.filter { isModule(it) }
		libraries.setFrom(dependencies.filter { !isModule(it) })
		// Tracks the modules as inputs, since they're no longer in libraries or
		// its incremental compilation snapshot. A change to one of them forces a
		// full rebuild.
		inputs.files(modules).withPropertyName("modulePath").withNormalizer(ClasspathNormalizer::class)
		// Mapped from elements, since the configuration cache would otherwise
		// resolve the argument before the jars of project dependencies are built.
		compilerOptions.freeCompilerArgs.addAll(modules.elements.map { elements ->
			if (elements.isEmpty()) emptyList()
			else listOf("-Xmodule-path=" + elements.joinToString(File.pathSeparator) { it.asFile.path })
		})
	}
}

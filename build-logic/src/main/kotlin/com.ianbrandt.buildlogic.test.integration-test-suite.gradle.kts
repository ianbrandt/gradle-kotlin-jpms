import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation.Companion.MAIN_COMPILATION_NAME

plugins {
	id("com.ianbrandt.buildlogic.kotlin-project")
	id("jvm-test-suite")
	id("com.ianbrandt.buildlogic.test.unit-test-suite")
}

val testSuiteName = "integrationTest"
val testSuiteDirName = "it"
val testSuiteTestSuffix = "IT"

val versionCatalog = versionCatalogs.named("libs")

@Suppress("UnstableApiUsage")
testing {
	suites {

		val test by getting(JvmTestSuite::class)

		register<JvmTestSuite>(testSuiteName) {

			sources {
				val sourcesRootDir = "src/$testSuiteDirName"
				java {
					setSrcDirs(listOf("$sourcesRootDir/java"))
				}
				kotlin {
					setSrcDirs(
						listOf(
							"$sourcesRootDir/kotlin",
							"$sourcesRootDir/java",
						)
					)
				}
				resources {
					setSrcDirs(listOf("$sourcesRootDir/resources"))
				}
			}

			targets {
				all {
					testTask.configure {
						filter {
							includeTestsMatching("*$testSuiteTestSuffix")
							// Support JUnit @Nested tests
							includeTestsMatching("*$testSuiteTestSuffix$*")
						}
						shouldRunAfter(test)
					}
				}
			}
		}
	}
}

dependencies {

	// Version catalog type-safe accessors not available in precompiled script
	// plugins: https://github.com/gradle/gradle/issues/15383

	"integrationTestImplementation"(
		platform("com.ianbrandt.platforms:test-platform")
	)

	"integrationTestImplementation"(
		versionCatalog.findLibrary("junit-api").get()
	)
}

kotlin {
	target {
		// Workaround for https://youtrack.jetbrains.com/issue/KTIJ-23114.
		compilations.getByName(testSuiteName)
			.associateWith(compilations.getByName(MAIN_COMPILATION_NAME))
	}
}

tasks {

	named<Task>("check").configure {
		val integrationTest by existing
		dependsOn(integrationTest)
	}
}

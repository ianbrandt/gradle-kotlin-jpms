pluginManagement {
	includeBuild("build-logic")
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
}

rootProject.name = "gradle-kotlin-jpms"

include("subprojects:demo")

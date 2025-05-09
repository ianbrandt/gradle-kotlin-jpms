pluginManagement {
	includeBuild("build-logic")
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "gradle-kotlin-jpms"

include("subprojects:java-named-module")
include("subprojects:named-module")

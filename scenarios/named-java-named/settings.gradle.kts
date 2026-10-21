pluginManagement {
	includeBuild("../../build-logic")
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
	versionCatalogs {
		create("libs") {
			from(files("../../gradle/libs.versions.toml"))
		}
	}
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "named-java-named"

include("subprojects:java-named-module")
include("subprojects:named-module")

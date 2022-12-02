pluginManagement {
	@Suppress("UnstableApiUsage")
	includeBuild("build-logic")
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
}

rootProject.name = "gradle-kotlin-jpms"

include("subprojects:named-module")
include("subprojects:unnamed-module")

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

rootProject.name = "named-mixed"

include("subprojects:named-module")

dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			from(files("../../gradle/libs.versions.toml"))
		}
	}
}

rootProject.name = "module-info-in-kotlin-src"

dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			from(files("../../gradle/libs.versions.toml"))
		}
	}
}

rootProject.name = "default-destination-dir"

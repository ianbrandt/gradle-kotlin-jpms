plugins {
	id("java-platform")
}

group = "com.ianbrandt.platforms"

dependencies {
	constraints {
		api(libs.kotlin.gradle.plugin.dependency)
		api(libs.gradle.idea.ext.plugin.gradle.plugin.dependency)
	}
}

plugins {
	id("java-platform")
}

group = "com.ianbrandt.platforms"

dependencies {
	constraints {
		api(libs.kotlin.gradle.plugin.dependency)
	}
}

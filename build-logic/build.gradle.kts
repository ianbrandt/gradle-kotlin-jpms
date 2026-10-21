plugins {
	`kotlin-dsl`
}

dependencies {

	implementation(platform("com.ianbrandt.platforms:plugins-platform"))

	implementation(libs.gradle.idea.ext.plugin.gradle.plugin.dependency)
	implementation(libs.kotlin.gradle.plugin.dependency)
}

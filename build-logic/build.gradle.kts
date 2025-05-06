plugins {
	`kotlin-dsl`
}

dependencies {

	implementation(platform("com.ianbrandt.platforms:plugins-platform"))

	implementation(libs.kotlin.gradle.plugin.dependency)
}

plugins {
	// Required for registering JPMS compiler arguments with IntelliJ per
	// https://youtrack.jetbrains.com/issue/IDEA-154038.
	alias(libs.plugins.gradle.idea.ext.plugin)
}

allprojects {
	group = "com.ianbrandt"
	version = "1.0-SNAPSHOT"
}

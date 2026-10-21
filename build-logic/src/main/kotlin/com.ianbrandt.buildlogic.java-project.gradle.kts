plugins {
	`java-library`
}

val javaTargetVersion = JavaVersion.VERSION_21.toString()

tasks {
	withType<JavaCompile>().configureEach {
		with(options) {
			release = javaTargetVersion.toInt()
			isFork = true
		}
	}
}

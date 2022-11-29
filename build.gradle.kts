import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL

allprojects {
	group = "com.ianbrandt"
	version = "1.0-SNAPSHOT"
}

tasks {

	named<Wrapper>("wrapper").configure {
		gradleVersion = "7.6"
		distributionType = ALL
	}
}

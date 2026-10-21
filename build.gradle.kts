import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL

// The wrapper shared by every build under scenarios/.
tasks {

	named<Wrapper>("wrapper").configure {
		gradleVersion = "9.8.2"
		distributionType = ALL
	}
}

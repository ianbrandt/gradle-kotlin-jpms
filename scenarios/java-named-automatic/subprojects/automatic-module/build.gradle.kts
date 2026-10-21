plugins {
	id("com.ianbrandt.buildlogic.kotlin-jpms-gradle-17271")
}

tasks {
	named<Jar>("jar").configure {
		manifest {
			attributes("Automatic-Module-Name" to "com.ianbrandt.jpms.auto")
		}
	}
}

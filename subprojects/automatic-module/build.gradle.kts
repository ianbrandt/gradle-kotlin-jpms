plugins {
	id("com.ianbrandt.buildlogic.kotlin-project")
}

tasks {
	named<Jar>("jar").configure {
		manifest {
			attributes("Automatic-Module-Name" to "com.ianbrandt.jpms.auto")
		}
	}
}

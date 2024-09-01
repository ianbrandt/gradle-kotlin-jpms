plugins {
	id("com.ianbrandt.buildlogic.kotlin-project")
}

dependencies {
	implementation(projects.subprojects.unnamedModule)
}

tasks {
	named<Jar>("jar").configure {
		manifest {
			attributes("Automatic-Module-Name" to "com.ianbrandt.jpms.auto")
		}
	}
}

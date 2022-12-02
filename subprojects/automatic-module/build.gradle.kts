plugins {
	id("com.ianbrandt.build.kotlin-project")
}

dependencies {
	implementation(project(":subprojects:unnamed-module"))
}

tasks {
	named<Jar>("jar").configure {
		manifest {
			attributes("Automatic-Module-Name" to "com.ianbrandt.jpms.auto")
		}
	}
}

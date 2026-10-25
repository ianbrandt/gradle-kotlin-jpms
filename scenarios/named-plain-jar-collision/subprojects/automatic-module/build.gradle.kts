plugins {
	id("com.ianbrandt.buildlogic.kotlin-jpms-gradle-17271")
}

tasks {
	named<Jar>("jar").configure {
		// No Automatic-Module-Name, so kotlinc names this jar's automatic module "shared" after the file
		archiveFileName.set("shared.jar")
	}
}

plugins {
	application
	id("com.ianbrandt.buildlogic.kotlin-project")
}

application {
	mainModule.set("com.ianbrandt.jpms.app")
	mainClass.set("com.ianbrandt.jpms.app.HelloWorldKt")
}

dependencies {
	implementation(projects.subprojects.otherNamedModule)
}

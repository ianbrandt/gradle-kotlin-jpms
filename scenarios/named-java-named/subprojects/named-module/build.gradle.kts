plugins {
	application
	id("com.ianbrandt.buildlogic.kotlin-jpms-gradle-17271")
}

application {
	mainModule.set("com.ianbrandt.jpms.app")
	mainClass.set("com.ianbrandt.jpms.app.HelloWorldKt")
}

dependencies {
	implementation(projects.subprojects.javaNamedModule)
}

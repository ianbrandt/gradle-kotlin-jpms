plugins {
	application
	id("com.ianbrandt.buildlogic.java-project")
}

application {
	mainModule.set("com.ianbrandt.jpms.app")
	mainClass.set("com.ianbrandt.jpms.app.HelloWorld")
}

dependencies {
	implementation(projects.subprojects.kotlinNamedModule)
}

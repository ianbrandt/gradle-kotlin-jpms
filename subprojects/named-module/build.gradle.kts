plugins {
	application
	id("com.ianbrandt.buildlogic.kotlin-project")
}

application {
	mainModule.set("com.ianbrandt.jpms.named")
	mainClass.set("com.ianbrandt.jpms.named.HelloWorld")
}

dependencies {
	implementation(projects.subprojects.automaticModule)
}

plugins {
	application
	id("com.ianbrandt.buildlogic.kotlin-jpms-gradle-17271")
}

application {
	mainModule.set("com.ianbrandt.jpms.named")
	mainClass.set("com.ianbrandt.jpms.named.HelloWorldKt")
}

dependencies {
	implementation(projects.subprojects.automaticModule)
}

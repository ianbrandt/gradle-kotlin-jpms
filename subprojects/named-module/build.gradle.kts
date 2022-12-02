plugins {
	application
	id("com.ianbrandt.build.kotlin-project")
}

application {
	mainModule.set("com.ianbrandt.jpms.named")
	mainClass.set("com.ianbrandt.jpms.named.HelloWorldKt")
}

dependencies {
	implementation(project(":subprojects:automatic-module"))
}

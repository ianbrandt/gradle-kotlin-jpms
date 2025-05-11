plugins {
	application
	id("com.ianbrandt.build.kotlin-project")
}

val mainModuleName = "com.ianbrandt.jpms.demo"

application {
	mainModule.set(mainModuleName)
	mainClass.set("com.ianbrandt.jpms.HelloWorldKt")
}

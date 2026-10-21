plugins {
	application
	id("com.ianbrandt.buildlogic.kotlin-jpms-gradle-17271")
}

val mainModuleName = "com.ianbrandt.jpms.demo"

application {
	mainModule.set(mainModuleName)
	mainClass.set("com.ianbrandt.jpms.HelloWorldKt")
}

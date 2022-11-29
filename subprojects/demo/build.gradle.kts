plugins {
	application
	id("com.ianbrandt.build.kotlin-project")
	id("com.ianbrandt.build.test.unit-test-suite")
}

application {
	mainModule.set("com.ianbrandt.jpms.demo")
	mainClass.set("com.ianbrandt.jpms.HelloWorld")
}

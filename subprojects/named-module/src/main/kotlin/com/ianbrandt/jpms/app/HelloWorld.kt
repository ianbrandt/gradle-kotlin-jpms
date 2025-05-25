package com.ianbrandt.jpms.app

import com.ianbrandt.jpms.lib.Greeter

// Must add `--patch-module /path/to/build/classes/kotlin` to the VM options of
// the Kotlin-type run configuration to run this from IntelliJ:
//https://youtrack.jetbrains.com/issue/KT-55389.
fun main() {
	println(HelloWorld.sayHello())
}

object HelloWorld {

	fun sayHello() = Greeter.sayHello()
}

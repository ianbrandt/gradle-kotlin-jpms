package com.ianbrandt.jpms.app

import com.ianbrandt.jpms.lib.Greeter

fun main() {
	println(HelloWorld.sayHello())
}

object HelloWorld {

	fun sayHello() = Greeter.sayHello()
}

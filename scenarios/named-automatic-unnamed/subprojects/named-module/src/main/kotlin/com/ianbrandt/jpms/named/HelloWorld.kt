package com.ianbrandt.jpms.named

import com.ianbrandt.jpms.auto.AutomaticGreeter

fun main() {
	println(HelloWorld.sayHello())
}

object HelloWorld {

	fun sayHello(): String = AutomaticGreeter.sayHello()
}

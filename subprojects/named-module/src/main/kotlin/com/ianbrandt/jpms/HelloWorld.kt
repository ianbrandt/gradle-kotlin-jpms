package com.ianbrandt.jpms

import com.ianbrandt.legacy.LegacyGreeter

fun main() {
	println(HelloWorld.sayHello())
}

object HelloWorld {

	fun sayHello() = LegacyGreeter.sayHello()
}

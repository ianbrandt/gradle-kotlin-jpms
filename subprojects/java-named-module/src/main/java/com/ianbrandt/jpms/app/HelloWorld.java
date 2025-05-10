package com.ianbrandt.jpms.app;

import com.ianbrandt.jpms.lib.Greeter;

public final class HelloWorld {

	private HelloWorld() {
	}

	public static void main(String[] args) {
		System.out.println(Greeter.sayHello());
	}
}

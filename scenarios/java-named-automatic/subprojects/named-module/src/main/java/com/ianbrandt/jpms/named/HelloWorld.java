package com.ianbrandt.jpms.named;

import com.ianbrandt.jpms.auto.AutomaticGreeter;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println(AutomaticGreeter.INSTANCE.sayHello());
	}
}

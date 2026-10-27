package com.ianbrandt.jpms.app;

public final class Main {

	private Main() {
	}

	public static void main(String[] args) {
		System.out.println(HelloWorld.INSTANCE.sayHello());
	}
}

package com.ianbrandt.jpms.auto;

public final class AutomaticGreeter {

	private AutomaticGreeter() {}

	public static AutomaticGreeter INSTANCE = new AutomaticGreeter();

	public String sayHello() {
		return "Hello, I'm an automatic module!";
	}
}

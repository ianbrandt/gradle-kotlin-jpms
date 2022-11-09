// Per https://github.com/gradle/gradle/issues/17271, module-info.java is only
// supported in src/main/java, not src/main/kotlin.
module com.ianbrandt.jpms {
	exports com.ianbrandt.jpms;
	requires kotlin.stdlib;
}

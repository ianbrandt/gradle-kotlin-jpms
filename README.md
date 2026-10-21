# Gradle Kotlin JPMS Reproducer

Reproducer for issues with Gradle support of Java Platform Module System modules implemented in Kotlin.

Each reproducer is a standalone Gradle build under `scenarios/`. The builds share the wrapper, `gradle.properties`, the version catalog, `build-logic`, and `platforms` at the repository root. Run one with:

```bash
./gradlew -p scenarios/named clean build
```

`./check-scenarios.sh` runs every scenario with the build cache off and compares each outcome with the scenario's `expected` file. Outcomes are from `clean build` and `clean run` with Gradle 9.8.2, Kotlin 2.4.21, and JDK 21.

| Scenario | Setup | Outcome |
|---|---|---|
| [`named`](scenarios/named) | One Kotlin named module, compiled to the Java destination directory per [gradle/gradle#17271](https://github.com/gradle/gradle/issues/17271) | Builds and runs |
| [`default-destination-dir`](scenarios/default-destination-dir) | `named` without the #17271 workaround | `compileJava` fails with `package is empty or does not exist: com.ianbrandt.jpms` |
| [`module-info-in-kotlin-src`](scenarios/module-info-in-kotlin-src) | `named` with `module-info.java` under `src/main/kotlin` | Builds, but `run` fails with `Module com.ianbrandt.jpms not found` |
| [`package-empty`](scenarios/package-empty) | One Kotlin named module in a subproject, without the empty Java class once needed to avoid `package is empty or does not exist` | Builds and runs |
| [`named-named`](scenarios/named-named) | A Kotlin named module that requires another Kotlin named module | Builds and runs |
| [`named-java-named`](scenarios/named-java-named) | A Kotlin named module that requires a Java named module | Builds and runs |
| [`named-java-named-KT-55389`](scenarios/named-java-named-KT-55389) | `named-java-named` with the [KT-55389](https://youtrack.jetbrains.com/issue/KT-55389) workaround in place of the #17271 one | Builds and runs |
| [`java-named-kotlin-named`](scenarios/java-named-kotlin-named) | A Java named module that requires a Kotlin named module | Builds and runs |
| [`java-named-kotlin-named-KT-55389`](scenarios/java-named-kotlin-named-KT-55389) | `java-named-kotlin-named` with the KT-55389 workaround in place of the #17271 one | Builds and runs |
| [`named-automatic`](scenarios/named-automatic) | A Kotlin named module that requires a Kotlin automatic module | Builds and runs |
| [`java-named-automatic`](scenarios/java-named-automatic) | A Java named module that requires a Java automatic module | Builds and runs |
| [`named-automatic-unnamed`](scenarios/named-automatic-unnamed) | A Kotlin named module that requires a Kotlin automatic module, which depends on a Kotlin unnamed module | Builds and runs |
| [`named-reads-unnamed`](scenarios/named-reads-unnamed) | A Kotlin named module that reads a Kotlin unnamed module with `--add-reads` | `compileKotlin` fails with `Symbol is declared in module 'unnamed.module', which the current module does not depend on`, pending [KT-20740](https://youtrack.jetbrains.com/issue/KT-20740) |
| [`java-named-reads-unnamed`](scenarios/java-named-reads-unnamed) | A Java named module that reads a Kotlin unnamed module with `--add-reads` | Builds and runs |
| [`java-named-reads-unnamed-gradle-idea-ext-plugin`](scenarios/java-named-reads-unnamed-gradle-idea-ext-plugin) | `java-named-reads-unnamed`, with `--add-reads` passed to IntelliJ IDEA's javac through the gradle-idea-ext plugin per [IDEA-154038](https://youtrack.jetbrains.com/issue/IDEA-154038) | Builds and runs |

With the #17271 workaround, `compileJava` fails with `package is empty or does not exist` for the Kotlin named module whenever `compileKotlin` is restored from the build cache, as when `clean run` follows `clean build`. With the build cache off, it still fails intermittently after `clean`.

Each scenario was previously a branch of the same name, with `named` on `main`. Those branches are kept for existing links, but they are no longer updated.

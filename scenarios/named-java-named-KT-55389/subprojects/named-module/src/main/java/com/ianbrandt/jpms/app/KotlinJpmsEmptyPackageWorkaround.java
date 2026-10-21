package com.ianbrandt.jpms.app;

// Work around "java.lang.module.InvalidModuleDescriptorException:
// Package com.ianbrandt.jpms.app not found in module" error when running from
// IntelliJ: https://youtrack.jetbrains.com/issue/KT-55389.
@SuppressWarnings("unused")
class KotlinJpmsEmptyPackageWorkaround {}

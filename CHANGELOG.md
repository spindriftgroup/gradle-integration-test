1.2.0
=====
- Upgrade gradle wrapper and use Gradle 4 API
- Implement change recommended by:
```
The setTestClassesDir(File) method has been deprecated and is scheduled to be removed in Gradle 5.0. Please use the setTestClassesDirs(FileCollection) method instead.
        at integration_test_8pbkub9sxaxbewek8v450fnlp$_run_closure1.doCall(/home/vagrant/workspace/common/buildtools/gradle/integration-test.gradle:3)
        (Run with --stacktrace to get the full stack trace of this deprecation warning.)
```

1.1.1
=====
- Version change only as portal publishing failed for 1.1.0

1.1.0
=====
- Set source and target compatibility to ensure it runs on JDK6

1.0.0
=====
- Initial version. Tested on gradle 2.14.1 only. (Known deprecations for 4.x)
- (Retrospectively this only runs on JDK8)
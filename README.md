integration-test
================
Creates build configuration for separate integration tests.
Provides configurable defaults for names and locations of output.

Usage
=====
Build script snippet for use in all Gradle versions
```$xslt
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "com.spindrift:integration-test:1.1.0"
  }
}

apply plugin: "com.spindrift.integration-test"
``` 

Custom Tasks
============

`integrationTest` - Executes the integration tests

Default Configuration
=====================

- The task name is integrationTest and is not modifiable
- Sets an integrationTestRuntime dependency on the testRuntime for convenience.  
  Additional compile and runtime dependencies must be set in the build configuration as normal
- Sets a dependency from the default check task on integrationTest (configurable below).
- Sets a dependency between the integrationTest task and its related compile task and the main source compile task.
- Forces the integrationTests to run after unit tests (configurable below)
- **_Note_**: As the sourceSet is named integrationTest, even though the source directories default to `src/integration-test/java` gradle will still look for `src/integrationTest/java` as well.

Optional Configuration
======================

Property | Default Value | Description  
-------- | ------------- | -----------  
javaSourceDir | `src/integration-test/java` | The sourceSet source path  
resourcesSourceDir | `src/integration-test/resources` | The sourceSet resources path   
checkDependsOnIntegrationTest | `true` | Optionally sets the check task to depend on integrationsTest  
mustRunAfterTest | `true` | Optionally sets integration tests to run after unit tests  

**_Note_** All configuration is applied post evaluation of the task graph generation.

Example configuration overrides
===============================

```$xslt
  integrationTest {
    javaSourceDir 'src/intTest/java'
    resourcesSourceDir 'src/intTest/resources'
    checkDependsOnIntegrationTest false
    mustRunAfterTest false
  }
```

Build Notes
===========

1. Maven local installation  
`gradle or gradle publishToMavenlocal`  
2. Publishing to Plugin portal  
`gradle clean build -Prelease=true publishPlugins`  
3. Publishing to Bintray JCenter  
`gradle clean build -Prelease=true bintrayUpload`  

Versions
========

See CHANGELOG
  






 
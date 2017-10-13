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
    classpath "com.spindrift:integration-test:1.0"
  }
}

apply plugin: "com.spindrift.integration-test"
``` 

Custom Tasks
============

integrationTest - Executes the integration tests

Optional Configuration
======================

Property | Default Value | Description  
-------- | ------------- | -----------  
javaSourceDir | 'src/integration-test/java' | The sourceSet source path  
resourcesSourceDir | 'src/integration-test/resources' | The sourceSet resources path  
runtimeDependsOnTestRuntime | true | Optionally sets an integrationTestRuntime dependency on the testRuntime  
checkDependsOnIntegrationTest | true | Optionally sets the check task to depend on integrationsTest  
mustRunAfterTest | true | Optionally sets integration tests to run after unit tests  

Versions
========

Version | Gradle Build Version | Compatible With 
------- | -------------------- | ---------------
1.0     | 2.14.1 | 2.14, not tested with 3.x, 4.x  
  






 
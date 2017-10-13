/**
 * Copyright (C) 2012-2017 Spindrift B.V. All Rights Reserved
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spindrift.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.testing.Test

/**
 * Adds an integration test task and configuration to a gradle project.
 * Configuration defaults and overrides are made via the gradle extension mechanism
 *
 * @author hallatech
 *
 */
class IntegrationTestPlugin implements Plugin<Project> {

  static final String PLUGIN_EXTENSION_NAME="integrationTest"
  static final String INTEGRATION_TEST_TASK="integrationTest"
  static final String INTEGRATION_TEST_TASK_DESCRIPTION="Runs the integration tests."
  static final String INTEGRATION_TEST_TASK_GROUP="Verification"
  static final String JAR_PATH_SYSTEM_PROPERTY="jar.path"
  static final String CLASSES_TASK="classes"
  static final String TEST_TASK="test"
  static final String INTEGRATION_TEST_CLASSES_TASK="integrationTestClasses"
  static final String DEFAULT_OUTPUT_DIR="integration-test"
  static final String HTML_DESTINATION_DIR="reports/${DEFAULT_OUTPUT_DIR}"

  @Override
  void apply(Project project) {

    project.extensions."${PLUGIN_EXTENSION_NAME}" = new IntegrationTestPluginExtension()

    configureSourceSets(project)
    configureMainSourceDependency(project)
    addDefaultRuntimeDependency(project)
    addIntegrationTestTask(project,INTEGRATION_TEST_TASK)
    addOptionalCheckDependency(project)
    addOptionalMustRunAfterTestDependency(project)

  }

  private configureSourceSets(Project project) {
    project.sourceSets {
      integrationTest
    }
    project.afterEvaluate {
      project.sourceSets {
        integrationTest {
          java.srcDir project.file(project[PLUGIN_EXTENSION_NAME].javaSourceDir)
          resources.srcDir project.file(project[PLUGIN_EXTENSION_NAME].resourcesSourceDir)
        }
      }
    }
  }

  private configureMainSourceDependency(Project project) {
    project.dependencies {
      integrationTestCompile(project.sourceSets.main.output)
    }
  }

  private addDefaultRuntimeDependency(Project project) {
    project.dependencies {
      integrationTestRuntime(project.configurations.testRuntime)
    }
  }

  private addIntegrationTestTask(Project project, String taskName) {
    Task task = project.getTasks().create(taskName,Test)
    task.setDependsOn([CLASSES_TASK,INTEGRATION_TEST_CLASSES_TASK,])
    task.setDescription(INTEGRATION_TEST_TASK_DESCRIPTION)
    task.setGroup(INTEGRATION_TEST_TASK_GROUP)


    task.testClassesDir = project.sourceSets.integrationTest.output.classesDir
    task.classpath = project.sourceSets.integrationTest.runtimeClasspath
    task.systemProperty(JAR_PATH_SYSTEM_PROPERTY, project.tasks.jar.archivePath)
    task.reports.html.destination = project.file("${project.buildDir}/${HTML_DESTINATION_DIR}")
    task.reports.junitXml.destination  = project.file("${task.reports.junitXml.destination}/${DEFAULT_OUTPUT_DIR}")

  }

  private addOptionalCheckDependency(Project project) {
    project.afterEvaluate {
      if (project[PLUGIN_EXTENSION_NAME].checkDependsOnIntegrationTest) {
        project.check.dependsOn "${INTEGRATION_TEST_TASK}"
      }
    }
  }

  private addOptionalMustRunAfterTestDependency(Project project) {
    project.afterEvaluate {
      if (project[PLUGIN_EXTENSION_NAME].mustRunAfterTest) {
        project.tasks."${INTEGRATION_TEST_TASK}".mustRunAfter TEST_TASK
      }
    }
  }

}

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
/**
 * Provides configuration for the integration test plugin
 * All properties can be set directly or using its DSL equivalent
 * e.g.
 * <code>
 * integrationTest {
 *   javaSourceDir='path/to/java' //Direct setting
 *   javaSourceDir 'path/to/java' //Optional DSL style setting
 * }
 * </code>
 *
 * @author hallatech
 *
 */

class IntegrationTestPluginExtension {

  /**
   * Configures and overrides
   *   sourceSets {
   *     integrationTest {
   *       java.srcDir
   *     }
   *   }
   */
  String javaSourceDir = 'src/integration-test/java'
  void javaSourceDir(String javaSourceDir) {
    this.javaSourceDir = javaSourceDir
  }

  /**
   * Configures and overrides
   *   sourceSets {
   *     integrationTest {
   *       resources.srcDir
   *     }
   *   }
   */
  String resourcesSourceDir = 'src/integration-test/resources'
  void resourcesSourceDir(String resourcesSourceDir) {
    this.resourcesSourceDir = resourcesSourceDir
  }

  boolean checkDependsOnIntegrationTest = true
  void checkDependsOnIntegrationTest(boolean checkDependsOnIntegrationTest) {
    this.checkDependsOnIntegrationTest = checkDependsOnIntegrationTest
  }

  boolean mustRunAfterTest = true
  void mustRunAfterTest(boolean mustRunAfterTest) {
    this.mustRunAfterTest = mustRunAfterTest
  }
}

rootProject.name = "popug-jira"

include("common")

include("event")

include("task:domain")
include("task:use-case")
include("task:persistence")

include("account:domain")
include("account:use-case")
include("account:persistence")


pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.springframework.boot" -> {
                    val springBootVersion = gradle.rootProject.properties["spring-boot.plugin.version"] as String
                    useVersion(springBootVersion)
                }
                "io.spring.dependency-management" -> {
                    val dependencyManagementVersion =
                        gradle.rootProject.properties["spring-dependency-management.plugin.version"] as String
                    useVersion(dependencyManagementVersion)
                }
                "org.jetbrains.kotlin.jvm" -> {
                    val kotlinJvmVersion = gradle.rootProject.properties["kotlin-jvm.plugin.version"] as String
                    useVersion(kotlinJvmVersion)
                }
                "org.jetbrains.kotlin.plugin.jpa" -> {
                    val kotlinJpaVersion = gradle.rootProject.properties["kotlin-jpa.plugin.version"] as String
                    useVersion(kotlinJpaVersion)
                }
                "org.jetbrains.kotlin.plugin.spring" -> {
                    val kotlinSpringVersion = gradle.rootProject.properties["kotlin-spring.plugin.version"] as String
                    useVersion(kotlinSpringVersion)
                }
            }
        }
    }
}

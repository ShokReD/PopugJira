plugins {
    kotlin("jvm").version("1.4.32")
}

ext["spring-version"] = "2.4.3"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
    }

    nonCircularDependency("event")
    nonCircularDependency("common")
}

fun Project.nonCircularDependency(projectName: String) {
    if (this.name != projectName) {
        dependencies {
            implementation(project(":${projectName}"))
        }
    }
}

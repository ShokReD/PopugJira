plugins {
    kotlin("jvm").version("1.4.32")
}

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
}

fun Project.nonCircularDependency(projectName: String) {
    if (this.name != projectName) {
        dependencies {
            implementation(project(":${projectName}"))
        }
    }
}
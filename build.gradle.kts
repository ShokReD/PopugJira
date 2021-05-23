plugins {
    kotlin("jvm")
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
    nonCircularDependency("common")

    tasks.withType(Test::class) {
        useJUnitPlatform()
    }
}

fun Project.nonCircularDependency(projectName: String) {
    if (this.name != projectName) {
        dependencies {
            implementation(project(":${projectName}"))
        }
    }
}

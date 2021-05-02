plugins {
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.jpa") version "1.3.72"
}

dependencies {
    implementation(project(":task:domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${springVersion()}")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

fun springVersion(): String {
    return rootProject.properties["spring-version"] as String
}

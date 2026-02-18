plugins {
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.TallerWapo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.sparkjava:spark-core:2.9.4")
    implementation("org.xerial:sqlite-jdbc:3.51.1.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.slf4j:slf4j-simple:2.0.17")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.20.1")
}

application {
    mainClass = "com.TallerWapo.Main"
}

tasks.test {
    useJUnitPlatform()
}

plugins {
    `java-library`
}

group = "com.TallerWapo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("com.sparkjava:spark-core:2.9.4") // Para servidor HTTP y rutas simples
    implementation ("org.xerial:sqlite-jdbc:3.51.1.0")   // Driver JDBC para SQLite
    implementation ("com.google.code.gson:gson:2.10.1")  // Para JSON (simple y libre)
    implementation("org.slf4j:slf4j-simple:2.0.17")  // Binder simple para SLF4J, tema de logs
}

tasks.test {
    useJUnitPlatform()
}
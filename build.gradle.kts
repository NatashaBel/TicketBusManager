plugins {
    id ("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"
description = "BusTicket"

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // Lombok
    implementation ("org.projectlombok:lombok:1.18.34")

    // Jackson Core and Databind
    implementation ("com.fasterxml.jackson.core:jackson-core:2.17.2")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.17.2")

    // JUnit for testing
    testImplementation ("junit:junit:3.8.1")

    annotationProcessor ("org.projectlombok:lombok:1.18.34")
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
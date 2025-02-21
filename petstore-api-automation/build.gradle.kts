plugins {
    kotlin("jvm") version "1.9.0"
    id("io.qameta.allure") version "2.11.2"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.cucumber:cucumber-java:7.14.0")
    testImplementation("io.cucumber:cucumber-junit:7.14.0")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("net.serenity-bdd:serenity-cucumber:3.9.2")
    testImplementation("net.serenity-bdd:serenity-core:3.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    // Logging
    implementation("org.slf4j:slf4j-simple:2.0.7")
}


tasks.test {
    useJUnitPlatform()
}

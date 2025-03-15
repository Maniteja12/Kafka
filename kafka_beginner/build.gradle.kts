plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

allprojects{
    group = "com.learnkafka"
    version = "1.0-SNAPSHOT"

    repositories {
        apply(plugin = "java")
        mavenCentral()
    }
}

subprojects {
    dependencies {
        implementation(group =  "org.apache.kafka", name = "kafka-clients", version = "3.6.1")
        implementation(group =  "com.fasterxml.jackson.core", name = "jackson-databind", version = "2.10.1")
        implementation(group =  "ch.qos.logback", name = "logback-classic", version = "1.2.3")
        implementation(group= "org.slf4j", name= "slf4j-simple", version= "1.7.25")
        testImplementation(platform("org.junit:junit-bom:5.9.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }
}
tasks.test {
    useJUnitPlatform()
}
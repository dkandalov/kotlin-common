import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
    java
    kotlin("jvm") version "1.3.72"
    `maven-publish`
}
group = "nonstdlib"
version = "0.2"

repositories {
	mavenCentral()
	maven { setUrl("https://dl.bintray.com/dkandalov/maven") }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
	testImplementation("junit:junit:4.12")
	testImplementation("datsok:datsok:0.2")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}

java {
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("nonstdlib") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = URI("https://api.bintray.com/maven/dkandalov/maven/nonstdlib/;publish=1")
            credentials {
                username = System.getenv("BINTRAY_USER")
                password = System.getenv("BINTRAY_API_KEY")
            }
        }
    }
}

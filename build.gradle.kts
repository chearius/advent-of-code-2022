import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.22"

    id("com.diffplug.spotless") version "6.12.0"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
    id("com.github.ben-manes.versions") version "0.44.0"
}

group = "dev.siller.advent-of-code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<DependencyUpdatesTask> {
    revision = "release"
    gradleReleaseChannel = "release"
    checkForGradleUpdate = true
    outputFormatter = "plain"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
    resolutionStrategy {
        componentSelection {
            all {
                val rejected = listOf("alpha", "beta", "rc", "preview", "eap")
                    .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-+]*") }
                    .any { it.matches(candidate.version) }

                if (rejected) {
                    reject("Release candidate")
                }
            }
        }
    }
}

spotless {
    kotlin {
        endWithNewline()
        trimTrailingWhitespace()
        ktlint()
    }
}

spotless {
    kotlinGradle {
        target("*.gradle.kts")

        endWithNewline()
        trimTrailingWhitespace()
        ktlint()
    }
}

detekt {
    allRules = true
    config = files("detekt-config.yml")
}

tasks.withType<Detekt> {
    jvmTarget = "18"
    ignoreFailures = false

    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(true)
        sarif.required.set(false)
    }
}

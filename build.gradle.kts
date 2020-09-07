import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin

val mysqlDriverVersion: String by project.extra
val kotlinjdbcVersion: String by project.extra
val okhttpVersion: String by project.extra
val wireMockStandaloneVersion: String by project.extra
val kotlinVersion: String by project.extra
val jacksonVersion: String by project.extra
val junitJupiterVersion: String by project.extra
val seleniumWebDriverVersion: String by project.extra
val selenideVersion: String by project.extra
val log4j2Version: String by project.extra
val restAssuredVersion: String by project.extra

plugins {
  kotlin("jvm") version "1.3.72"
}

group = "org.example"
version = "1.0-SNAPSHOT"


allprojects {
  repositories {
    mavenCentral()
    jcenter()
  }
}

subprojects {
  version = "1.0"
  apply<KotlinPlatformJvmPlugin>()

  dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("mysql:mysql-connector-java:$mysqlDriverVersion")
    implementation("com.vladsch.kotlin-jdbc:kotlin-jdbc:$kotlinjdbcVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.github.tomakehurst:wiremock:$wireMockStandaloneVersion")
    implementation("com.codeborne:selenide:$selenideVersion")
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumWebDriverVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    implementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4j2Version")
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
  }

  tasks.test {
    useJUnitPlatform()
    testLogging {
      events("passed", "skipped", "failed")
    }
  }

  tasks {
    compileKotlin {
      kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
      kotlinOptions.jvmTarget = "1.8"
    }
  }
}


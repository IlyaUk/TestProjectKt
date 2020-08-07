val allureOkhttpVersion: String by project.extra
val allureVersion: String by project.extra

plugins {
  id("io.qameta.allure") version "2.8.1"
}

allure {
  version = allureVersion
  autoconfigure = true
  clean = true

  useJUnit5 {
    version = allureVersion
  }
}

dependencies {
  implementation("io.qameta.allure:allure-okhttp3:$allureOkhttpVersion")
  implementation("io.qameta.allure:allure-selenide:$allureVersion")
}

tasks.register<Test>("selenideConfigTests") {
  useJUnitPlatform()
  filter {
    includeTestsMatching("selenide.*")
  }
}

tasks.register<Test>("seleniumConfigTests") {
  useJUnitPlatform()
  filter {
    includeTestsMatching("selenium.*")
  }
  failFast = true
}

tasks.register<Test>("runAllConfigTests") {
  useJUnitPlatform()
  filter {
    includeTestsMatching("selenium.*")
    includeTestsMatching("selenide.*")
    includeTestsMatching("config.*")
  }
  maxParallelForks = 2
}
pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        //bat 'gradle clean :core:test'
        bat 'gradle build'
      }
    }
    stage('test') {
      steps {
        bat 'gradle :core:runAllConfigTests'
      }
    }
    stage('Allure report') {
      allure includeProperties: false, jdk: '', results: [[path: 'core\\build\\allure-results']]
    }
  }
}


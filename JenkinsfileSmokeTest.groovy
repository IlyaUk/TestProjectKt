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
      steps {
        allure([includeProperties: false, jdk: '', results: [[path: 'core\\build\\allure-results']]])
      }
    }
  }
}


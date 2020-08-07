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
        bat 'gradle runAllConfigTests'
      }
    }
  }
}
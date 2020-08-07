pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        bat 'gradle clean :core:test'
      }
    }
  }
}
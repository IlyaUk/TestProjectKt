pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        bat 'clean :core:test'
      }
    }
  }
}
pipeline {
  agent any
  stages {
    stage('Git checkout') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/add-jenkins-pipeline']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/IlyaUk/TestProjectKt/']]])
      }
    }
    stage('build') {
      steps {
        bat 'gradle build -x test'
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


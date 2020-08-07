pipeline {
  agent any
  stages {
    stage('Git checkout') {
      steps {
        checkout([$class: 'GitSCM',
                  branches: [[name: '*/add-jenkins-pipeline']],
                  doGenerateSubmoduleConfigurations: false,
                  extensions: [],
                  submoduleCfg: [],
                  userRemoteConfigs: [[url: 'https://github.com/IlyaUk/TestProjectKt/']]])
      }
    }
    stage('Build project') {
      steps {
        bat 'gradle build -x test'
      }
    }
    stage('Run tests') {
      steps {
        bat 'gradle :core:runAllConfigTests'
      }
    }
    stage('Allure report') {
      steps {
        allure([includeProperties: false,
                jdk: '',
                results: [[path: 'core\\build\\allure-results']]])
      }
    }
    stage('Gradle report') {
      steps {
        publishHTML(
            [allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: false,
             reportDir: 'core\\build\\reports\\tests\\test',
             reportFiles: 'index.html',
             reportName: 'HTML Report - Gradle',
             reportTitles: ''])
      }
    }
  }
}


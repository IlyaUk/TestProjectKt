pipeline {
  agent any
  stages {
    stage('Git checkout') {
      steps {
        checkout([$class                           : 'GitSCM',
                  branches                         : [[name: '*/add-jenkins-pipeline']],
                  doGenerateSubmoduleConfigurations: false,
                  extensions                       : [],
                  submoduleCfg                     : [],
                  userRemoteConfigs                : [[url: 'https://github.com/IlyaUk/TestProjectKt/']]])
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
  }
  post {
    always {
      script {
        allure([
            includeProperties: false,
            jdk              : '',
            results          : [[path: 'core\\build\\allure-results']]
        ])
      }
      script {
        publishHTML([
            allowMissing         : false,
            alwaysLinkToLastBuild: true,
            keepAll              : false,
            reportDir            : 'core\\build\\reports\\tests\\runAllConfigTests',
            reportFiles          : 'index.html',
            reportName           : 'Gradle Report',
            reportTitles         : ''
        ])
      }
      script {
        junit allowEmptyResults: true, testResults: '**/core/build/test-results/**/*.xml'
      }
    }
  }
}
pipeline {
  agent any
  stages {
    stage('Git checkout') {
      steps {
        checkout([$class                           : 'GitSCM',
                  branches                         : [[name: '*/configure-docker']],
                  doGenerateSubmoduleConfigurations: false,
                  extensions                       : [],
                  submoduleCfg                     : [],
                  userRemoteConfigs                : [[url: 'https://github.com/IlyaUk/TestProjectKt/']]])
      }
    }
    stage('Build project') {
      steps {
        bat 'gradle clean build -x test'
      }
    }
    stage('Run tests') {
      steps {
        bat 'gradle\
             -Dwebdriver.type=remote\
             -Dwebdriver.host=192.168.99.100\
             -Dwebdirver.port=4444\
             -Dbrowser.name="firefox"\
             -Dbrowser.headless="false"\
             :core:landingPageTests'
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
        publishHTML([
            allowMissing         : false,
            alwaysLinkToLastBuild: true,
            keepAll              : false,
            reportDir            : 'core\\build\\reports\\tests\\landingPageTests',
            reportFiles          : 'index.html',
            reportName           : 'Gradle Report',
            reportTitles         : ''
        ])
        junit allowEmptyResults: true, testResults: '**/core/build/test-results/**/*.xml'
        archiveArtifacts artifacts: '**/core/build/reports/**/*.*', followSymlinks: false
      }
    }
  }
}
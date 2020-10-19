pipeline {
  agent any

  parameters {
    string(name: 'autotestVersion', defaultValue: "1.0-SNAPSHOT", trim: true)
    string(name: 'branchToRunWith', defaultValue: "master", trim: true)
    booleanParam(name: 'isSavedToNexus', defaultValue: false,
        description: 'Set to save version mm-automation to Nexus'
    )
  }

  stages {
    stage('Git checkout') {
      steps {
        checkout([$class                           : 'GitSCM',
                  branches                         : [[name: '*/master']],
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
        bat 'gradle :core:runAllConfigTests'
      }
    }
  }
  post {
    always {
      script {
        chat_id = '-1001266979201'
        message = "Build results for ${env.JOB_NAME} - ${env.BUILD_URL}"
        telegramSend(message, chat_id)
        String obligatoryEmailLink = """
        <a href='${env.BUILD_URL}'>Autotests Internal Test Results After Merge to Master Branch- Build ${env.BUILD_ID}</a>
        """
        String emailBody = obligatoryEmailLink

        if (isSavedToNexus == "true") {
          String versionData = "<h2>Build version: $autotestVersion</h2> <h2>Branch name: $branchToRunWith</h2>"
          emailBody = "$emailBody\n$versionData"
        }
        emailext(
            subject: "[Autotests Internal Test Execution] ${currentBuild.currentResult}",
            body: emailBody,
            to: "ilya.uk@hotmail.com"
        )
        allure([
            includeProperties: false,
            jdk              : '',
            results          : [[path: 'core\\build\\allure-results']]
        ])
        publishHTML([
            allowMissing         : false,
            alwaysLinkToLastBuild: true,
            keepAll              : false,
            reportDir            : 'core\\build\\reports\\tests\\runAllConfigTests',
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

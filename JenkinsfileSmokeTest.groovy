final TRIGGERED_BY_UPSTREAM = "Triggered by upstream"

def sendTelegram(message) {
  def encodedMessage = URLEncoder.encode(message, "UTF-8")

  withCredentials([string(credentialsId: 'telegramToken', variable: 'TOKEN'),
                   string(credentialsId: 'telegramChatId', variable: 'CHAT_ID')]) {

    response = httpRequest(consoleLogResponseBody: true,
        contentType: 'APPLICATION_JSON',
        httpMode: 'POST',
        url: "https://api.telegram.org/bot$TOKEN/sendMessage?text=$encodedMessage&chat_id=$CHAT_ID&parse_mode=HTML",
        validResponseCodes: '200')
    return response
  }
}

pipeline {
  agent any

  parameters {
    string(name: 'autotestVersion', defaultValue: "1.0-SNAPSHOT", trim: true,
        description: 'Max. length - 30 symbols')
  }

  stages {
    stage('Validate build parameters') {
      steps {
        script {
          if ((params.autotestVersion).length() > 30) {
            error("Build failed because of incorrect size for autotestVersion parameter")
          }
        }
      }
    }
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
        wrap([$class: 'BuildUser']) {
          jobStartedBy = env.BUILD_USER_ID ?: ${TRIGGERED_BY_UPSTREAM}
        }
        message = """
      Build results
      job: ${env.JOB_NAME}
      result: ${currentBuild.currentResult}
      buildUrl: ${env.BUILD_URL}
      startedBy: $jobStartedBy
      autotestVersion: $autotestVersion
        """
        sendTelegram(message)
        String emailBody = """
        <a href='${env.BUILD_URL}'>Autotests Internal Test Results After Merge to Master Branch- Build ${env.BUILD_ID}</a>
        <h2>Build version: $autotestVersion</h2> <h2>Branch name: Master</h2>
        """
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

node {
    //Utilizing a try block so as to make the code cleaner and send slack notification in case of any error

    //Call function to send a message to Slack
    notifyBuild('STARTED')
    // Global variable declaration
    def project = 'android_test'
    def appName = 'Sample App AAC'

    // Stage, is to tell the Jenkins that this is the new process/step that needs to be executed
    stage('Checkout') {
        try {
          // Pull the code from the repo
          checkout scm
        } catch (e) {
            currentBuild.result = "FAILED"
            throw e
        } finally {
            notifyBuild(currentBuild.result)
        }
    }

    stage('Build') {
        try {
            sh './gradlew --refresh-dependencies clean assemble'
            //lock('emulator') {
            //    sh './gradlew connectedCheck'
            //}
            currentBuild.result = 'SUCCESS'
        } catch(error) {
            currentBuild.result = 'FAILURE'
            notifyBuild ("This build is broken ${env.BUILD_URL}")
        } finally {
            //junit '**/test-results/**/*.xml'
        }
   }
}

def notifyBuild(String buildStatus = 'STARTED') {
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  def color = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"
  def details = """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>"""

  if (buildStatus == 'STARTED') {
    color = 'YELLOW'
    colorCode = '#FFCC00'
  } else if (buildStatus == 'SUCCESSFUL') {
    color = 'GREEN'
    colorCode = '#228B22'
  } else {
    color = 'RED'
    colorCode = '#FF0000'
  }

  //slackSend (color: colorCode, message: summary)
}

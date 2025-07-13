pipeline {
    agent any   // use any available agent/node

    stages {
        stage('Checkout') {
            steps {
                // clone from your git repository
                git url: 'https://github.com/BhoiDnyaneshwar/Selenium-Java-Apps.git', branch: 'master'
            }
        }

        stage('Build & Run Tests') {
            steps {
                // run Maven clean and test
                bat  'mvn test'
            }
        }
        
        stage('Email'){
        steps{
        emailext body: 'Dnyane Started', subject: 'Cames from pipeline', to: 'dnyanumore97@gmail.com'
        }
        }

        stage('Archive Reports') {
            steps {
                // archive test reports, e.g., Surefire XML
                junit '**/target/surefire-reports/*.xml'
                // optional: archive HTML reports or screenshots
                archiveArtifacts artifacts: '**/target/screenshots/*.png', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished (success or fail).'
        }
        success {
            echo 'Build and tests passed!'
        }
        failure {
            echo 'Build or tests failed!'
        }
    }
}

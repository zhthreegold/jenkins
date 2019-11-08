pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /tmp:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'echo hello world'
            }
        }
    }
}

pipeline {
  agent { docker { image 'maven:3.3.3' } }
  stages {
    stage("stage 1") {
      steps {
        sh 'docker -v'
        sh 'docker-compose -v'
      }
    }
  }
}

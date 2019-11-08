pipeline {
  stages {
    stage("stage 1") {
      steps {
        sh 'docker -v'
        sh 'docker-compose -v'
      }
    }
  }
}

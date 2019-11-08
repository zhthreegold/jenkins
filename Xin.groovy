pipeline {
   agent any

   stages {
      stage('Hello') {
         steps {
            echo 'Hello World'
            sh "docker -v"
            sh "docker-compose -v"
         }
      }
   }
}

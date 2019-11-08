// Runs the tr-stack unit tests using the 'tr-stack-unitTest' service in the docker-compose file
// which exists in 'tr-containers' GitHub repository

currentBuild.description = ''

def git, aws
/* node {
    def rootDir = pwd()
    git = load "${rootDir}@script/jenkins/scripts/pipeline/common/git.groovy"
    aws = load "${rootDir}@script/jenkins/scripts/pipeline/common/aws.groovy"
    notify = load "${rootDir}@script/jenkins/scripts/pipeline/common/notify.groovy"
} */

node('Docker') { ansiColor('xterm') { timestamps {
    sh "sudo rm -rf *" // clean up
    echo "Using trStackRef: ${trStackRef}"

    try {
        currentBuild.description += "branch: ${trStackBranch} commit: ${trStackRef}"
        env.DOCKER_PROJECTS = env.WORKSPACE
        env.DOCKER_CLIENT_TIMEOUT=120
        env.COMPOSE_HTTP_TIMEOUT=120

        sh 'sudo chown -R jenkins .' // allow deletion of files created by 'root' in docker

        /* stage('Check Out Repositories') {
            dir('repos') {
                dir('tr-stack') {
                    checkout([$class: 'GitSCM', branches: [[name: trStackRef]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CheckoutOption'], [$class: 'CloneOption', depth: 0, noTags: true, reference: '', shallow: true]], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/TradeRev/tr-stack']]])
                }
                dir('tr-containers') {
                    git.checkoutTrContainers()
                }
            }
        }
        stage('Build Docker Images') {
            dir('repos/tr-containers/tr-stack-test') {
                sh './build_docker.sh'
            }
        } */
        stage('Run Tests') {
            /* dir('repos/tr-containers') {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                                  credentialsId: 'aws-devops', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                    sh """
                    docker-compose -v
                    docker -v
                    """
                }
            } */
            sh """
            docker-compose -v
            docker -v
            """
        }
      //  stage('Publish Test Results') {
      //      junit '**/*TestResults.xml'
      //  }

    }
    catch (exc) {
        echo "Something went wrong, build failed."
        throw exc
    }
} } }

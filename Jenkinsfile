def region = "ap-northeast-2"
def ecrUrl = "806246625807.dkr.ecr.ap-northeast-2.amazonaws.com"
def ec2Url = "ec2-3-36-118-140.ap-northeast-2.compute.amazonaws.com"
def repository = "credit-account"
def containerName = "credit-account-server"

def awsEcrCredential = "aws-ecr-push"
def sshCredential = "credit-account-server-ec2"

node {
    stage('Clone repository') {
        checkout scm
    }
    stage('Jib Build') {
        withAWS(region: region, credentials: awsEcrCredential) {
            ecrLogin()
            sh "./gradlew jib"
        }
    }
    stage('Deploy') {
        sshagent(credentials: [sshCredential]) {
            sh """
                ssh -o StrictHostKeyChecking=no ec2-user@${ec2Url} \
                'aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${ecrUrl} && \
                docker container rm -f ${containerName} && \
                docker container run -d -p 80:8080 --name ${containerName} \
                ${ecrUrl}/${repository}:latest;'
            """
        }
    }
}

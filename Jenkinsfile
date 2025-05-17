pipeline {
    agent any

    environment {
        IMAGE_NAME = 'your-dockerhub-username/airline-management-service:latest'
        EC2_USER = 'ubuntu'
        EC2_HOST = '13.203.221.108'
        EC2_KEY = credentials('ec2-ssh-key')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/AdibaAli26/Airline-Management-Service.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Docker Push') {
            steps {
               withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
    sh '''
        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
        docker push $IMAGE_NAME
    '''
}

            }
        }

        stage('Deploy on EC2') {
            steps {
                sshagent(['ec2-ssh-key']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no $EC2_USER@$EC2_HOST '
                            docker stop airline-app || true &&
                            docker rm airline-app || true &&
                            docker pull $IMAGE_NAME &&
                            docker run -d -p 8080:8080 --name airline-app $IMAGE_NAME
                        '
                    """
                }
            }
        }
    }
}

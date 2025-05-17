pipeline {
    agent any  // Run on a node/agent with Docker & Maven installed

    environment {
        JAR_NAME = 'target/airline-management-service.jar'
        EC2_USER = 'ubuntu'
        EC2_HOST = '13.203.221.108'
        EC2_KEY = credentials('ec2-ssh-key') // ensure this matches the ID in sshagent below
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
                sh 'docker build -t airline-management-service .'
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(['ec2-ssh-key']) {
                    sh """
                        scp -o StrictHostKeyChecking=no ${JAR_NAME} ${EC2_USER}@${EC2_HOST}:/home/${EC2_USER}/app.jar
                        ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} 'nohup java -jar /home/${EC2_USER}/app.jar > output.log 2>&1 &'
                    """
                }
            }
        }
    }
}

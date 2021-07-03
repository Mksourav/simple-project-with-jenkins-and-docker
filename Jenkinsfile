pipeline {
    agent any

    stages {
        
        stage('Build') {
            steps {
                dir("server/"){
                    sh 'mvn install -DskipTests'
                }
            }
        }
        stage('Testing Environment') {
            steps {
                dir("server/") {
                    sh 'mvn test -Dtest=IntegrationSuite'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'sudo docker-compose build'
                sh 'sudo docker-compose up -d'
            }
        }
        stage('Selenium Tests') {
            steps {
                dir("server/") {
                    sh 'mvn test -Dtest=SeleniumSuite'
                }
            }
        }
    }
}

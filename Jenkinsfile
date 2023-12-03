pipeline{
    agent any
    tools{
        maven 'MAVEN_HOME'
    }
    stages{
    	stage('Cleanup Workspace') {
            steps {
                deleteDir()
            }
        }
        stage('Clone code from Git'){
            steps{
            checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/walmart-2023-sep-ascend/Team06-Email-Notification.git']])
            }
        }
        stage('Build Application'){
            steps{
            bat 'mvn clean install'
            }
        }
        stage('Test Application'){
            steps{
            bat 'mvn test'
            }
        }
        stage('Code Analysis'){
            steps{
            bat 'mvn pmd:pmd'
            }
        }
        stage('Build Docker image'){
            steps{
                script{
                    bat 'docker build -t email-service .'
                }
            }
        }
        //stage('Push image to Docker hub'){
        //    steps{
        //        script{
        //            withCredentials([string(credentialsId: 'dockerpwd', variable: 'dockerpassword')]) {
        //            bat 'docker login -u radhika17 -p ${dockerpassword}'
        //            bat 'docker push inventory-update-docker.jar'
        //}
               // }
        //    }
        //}
    }
}

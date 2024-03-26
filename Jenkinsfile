pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Azure Login') {
            steps {
                withCredentials([azureServicePrincipal('1a4b5c71-4928-4f7f-a468-479af8aa3fa0')]) {
                    bat 'az login --service-principal -u %AZURE_CLIENT_ID% -p %AZURE_CLIENT_SECRET% -t %AZURE_TENANT_ID%'
                }
            }
        }

        stage('Deploy') {
            steps {
                bat 'az webapp deploy --resource-group TodoappRG --name todoapp-hristo --src-path ./target/todo-app.jar'
            }
        }
    }
}

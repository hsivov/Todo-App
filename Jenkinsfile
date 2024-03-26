pipeline {
    agent any

    environment {
        AZURE_RESOURCE_GROUP = 'TodoappRG'
        AZURE_LOCATION = 'west-europe'
        AZURE_APP_SERVICE_PLAN = 'ASP-todoapp-hristo'
        AZURE_WEB_APP = 'todoapp-hristo'
        AZURE_MYSQL_SERVER = 'todoapp-flexible-mysql-server'
        AZURE_MYSQL_DB = 'MyDatabase'
    }

    stages {
        stage('Create Azure Resource Group') {
            steps {
                bat "az group create --name %AZURE_RESOURCE_GROUP% --location %AZURE_LOCATION%"
            }
        }

        stage('Create App Service Plan') {
            steps {
                bat "az appservice plan create --name %AZURE_APP_SERVICE_PLAN% --resource-group %AZURE_RESOURCE_GROUP% --location %AZURE_LOCATION% --sku F1"
            }
        }

        stage('Create Web App') {
            steps {
                bat "az webapp create --name %AZURE_WEB_APP% --resource-group %AZURE_RESOURCE_GROUP% --plan %AZURE_APP_SERVICE_PLAN% --runtime 'java|17'"
            }
        }

        stage('Create MySQL Server and Database') {
            steps {
                bat """
                az mysql flexible-server create \
                    --resource-group %AZURE_RESOURCE_GROUP% \
                    --name %AZURE_MYSQL_SERVER% \
                    --location %AZURE_LOCATION% \
                    --sku-name Standard_D2ds_v4 \
                    --admin-user admin \
                    --admin-password 647E3IQ38IVK2VVK%24 \
                    --public-access Enabled
                """
                bat """
                az mysql flexible-server db create \
                    --resource-group %AZURE_RESOURCE_GROUP% \
                    --server-name %AZURE_MYSQL_SERVER% \
                    --name %AZURE_MYSQL_DB% \
                    --charset utf8 \
                    --collation utf8_general_ci
                """
            }
        }
    }
}
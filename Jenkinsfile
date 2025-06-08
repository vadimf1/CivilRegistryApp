pipeline {
    agent any

    triggers {
        cron('0 2 * * *')
    }

    parameters {
        string(defaultValue: "chrome", description: "Browser type", name: "BROWSER_TYPE")
        string(defaultValue: "1920x1080", description: "Browser size", name: "BROWSER_SIZE")
        string(description: "App login", name: "APP_LOGIN")
        string(description: "App password", name: "APP_PASSWORD")
        string(description: "Database login", name: "DB_LOGIN")
        string(description: "Database password", name: "DB_PASSWORD")
    }

    stages {
        stage('Build') {
            steps {
                script {
                    sh "./gradlew clean test -Ddb.login=${params.DB_LOGIN} -Ddb.password=${params.DB_PASSWORD} -Dapp.login=${params.APP_LOGIN} -Dapp.password=${params.APP_PASSWORD} -Dbrowser.type=${params.BROWSER_TYPE} -Dbrowser.size=${params.BROWSER_SIZE}"
                }
            }
        }
    }

    post {
        always {
            allure([
                reportBuildPolicy: "ALWAYS",
                results: [[path: "build/allure-results"]]
            ])
            emailext(
                subject: "SENLA-REGOFFICE: Allure Report for Build #${BUILD_NUMBER}",
                body: "Link to the Allure Report for Build #${BUILD_NUMBER}: ${env.BUILD_URL}allure/",
                to: "vadimfth@gmail.com"
            )
        }
    }
}

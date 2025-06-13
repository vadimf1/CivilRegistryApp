pipeline {
    agent any

    triggers {
        cron('0 2 * * *')
    }

    environment {
        JAVA_HOME = "/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    parameters {
        string(defaultValue: "chrome", description: "Browser type", name: "BROWSER_TYPE")
        string(defaultValue: "1920x1080", description: "Browser size", name: "BROWSER_SIZE")
        string(description: "App login", name: "APP_LOGIN")
        string(description: "App password", name: "APP_PASSWORD")
        string(description: "Database login", name: "DB_LOGIN")
        string(description: "Database password", name: "DB_PASSWORD")
        booleanParam(defaultValue: true, description: "Run all tests", name: "ALL")
        booleanParam(defaultValue: false, description: "Run ui tests", name: "UI")
        booleanParam(defaultValue: false, description: "Run api tests", name: "API")
    }

    stages {
        stage('all') {
            when {
                expression { return params.ALL }
            }
            steps {
                script {
                    sh "./gradlew clean test -Ddb.login=${params.DB_LOGIN} -Ddb.password=${params.DB_PASSWORD} -Dapp.login=${params.APP_LOGIN} -Dapp.password=${params.APP_PASSWORD} -Dbrowser.type=${params.BROWSER_TYPE} -Dbrowser.size=${params.BROWSER_SIZE}"
                }
            }
        }

        stage('ui') {
            when {
                expression { return params.UI }
            }
            steps {
                script {
                    sh "./gradlew clean testUi -Ddb.login=${params.DB_LOGIN} -Ddb.password=${params.DB_PASSWORD} -Dapp.login=${params.APP_LOGIN} -Dapp.password=${params.APP_PASSWORD} -Dbrowser.type=${params.BROWSER_TYPE} -Dbrowser.size=${params.BROWSER_SIZE}"
                }
            }
        }

        stage('api') {
            when {
                expression { return params.API }
            }
            steps {
                script {
                    sh "./gradlew clean testApi -Ddb.login=${params.DB_LOGIN} -Ddb.password=${params.DB_PASSWORD} -Dapp.login=${params.APP_LOGIN} -Dapp.password=${params.APP_PASSWORD} -Dbrowser.type=${params.BROWSER_TYPE} -Dbrowser.size=${params.BROWSER_SIZE}"
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
        }
    }
//
//     post {
//         always {
//             allure([
//                 reportBuildPolicy: "ALWAYS",
//                 results: [[path: "build/allure-results"]]
//             ])
//             emailext(
//                 subject: "SENLA-REGOFFICE: Allure Report for Build #${BUILD_NUMBER}",
//                 body: "Link to the Allure Report for Build #${BUILD_NUMBER}: ${env.BUILD_URL}allure/",
//                 to: "vadimfth@gmail.com"
//             )
//         }
//     }
}

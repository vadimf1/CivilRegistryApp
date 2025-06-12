FROM gradle:8.3.0-jdk17
WORKDIR /app
COPY . .
CMD ./gradlew test --tests "eu.senla.regoffice.ui.ApplicationAdministrationTests" \
    -Ddb.login=$DB_LOGIN \
    -Ddb.password=$DB_PASSWORD \
    -Dapp.login=$APP_LOGIN \
    -Dapp.password=$APP_PASSWORD \
    -Dbrowser.type=$BROWSER_TYPE \
    -Dbrowser.size=$BROWSER_SIZE && tail -f /dev/null

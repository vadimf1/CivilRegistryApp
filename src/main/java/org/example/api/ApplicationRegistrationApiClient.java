package org.example.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.models.ApplicationResponse;
import org.example.models.User;

public class ApplicationRegistrationApiClient {
    private final String URL = "https://regoffice.senla.eu/sendUserRequest";

    @Step("Создание заявки через API")
    public ApplicationResponse createApplication(User user) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic dXNlcjpzZW5sYXRlc3Q=")
                .body(user)
                .when()
                .post(URL)
                .then()
                .extract()
                .as(ApplicationResponse.class);
    }
}

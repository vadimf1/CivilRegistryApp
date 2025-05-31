package org.example.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.example.models.CreateApplicationResponse;
import org.example.models.GetApplStatusResponse;
import org.example.models.User;

import static org.example.api.RequestSpecs.authenticatedJsonSpec;

public class ApplicationRegistrationApiClient {
    private final String CREATE_APPLICATION_URL = "https://regoffice.senla.eu/sendUserRequest";
    private final String GET_APPLICATION_STATUS_URL = "https://regoffice.senla.eu/getApplStatus/";

    @Step("Создание заявки через API")
    public CreateApplicationResponse createApplication(User user) {
        return RestAssured.given()
                .spec(authenticatedJsonSpec())
                .body(user)
                .when()
                .post(CREATE_APPLICATION_URL)
                .then()
                .statusCode(200)
                .extract()
                .as(CreateApplicationResponse.class);
    }

    @Step("Получение статуса заявки через API")
    public GetApplStatusResponse getApplStatus(Integer applicationId) {
        return RestAssured.given()
                .spec(authenticatedJsonSpec())
                .when()
                .get(GET_APPLICATION_STATUS_URL + applicationId)
                .then()
                .statusCode(200)
                .extract()
                .as(GetApplStatusResponse.class);
    }
}

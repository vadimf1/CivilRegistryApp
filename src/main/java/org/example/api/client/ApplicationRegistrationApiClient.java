package org.example.api.client;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.example.models.CreateApplicationResponse;
import org.example.models.GetApplStatusResponse;
import org.example.models.User;
import org.example.utils.PropertyUtil;

import static org.example.api.spec.RequestSpecs.authenticatedJsonSpec;

public class ApplicationRegistrationApiClient {
    private final String CREATE_APPLICATION_ENDPOINT_KEY = "endpoint.createApplication";
    private final String GET_APPLICATION_STATUS_ENDPOINT_KEY = "endpoint.getApplStatus";

    @Step("Создание заявки через API")
    public CreateApplicationResponse createApplication(User user) {
        return RestAssured.given()
                .spec(authenticatedJsonSpec())
                .body(user)
                .when()
                .post(PropertyUtil.getProperty(CREATE_APPLICATION_ENDPOINT_KEY))
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
                .get(PropertyUtil.getProperty(GET_APPLICATION_STATUS_ENDPOINT_KEY) + "/" + applicationId)
                .then()
                .statusCode(200)
                .extract()
                .as(GetApplStatusResponse.class);
    }
}

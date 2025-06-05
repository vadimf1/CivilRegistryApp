package eu.senla.regoffice.api.client;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import eu.senla.regoffice.api.spec.RequestSpecs;
import eu.senla.regoffice.models.CreateApplicationResponse;
import eu.senla.regoffice.models.GetApplStatusResponse;
import eu.senla.regoffice.models.User;
import eu.senla.regoffice.utils.PropertyUtil;

public class ApplicationRegistrationApiClient {
    @Step("Создание заявки через API")
    public CreateApplicationResponse createApplication(User user) {
        return RestAssured.given()
                .spec(RequestSpecs.authenticatedJsonSpec())
                .body(user)
                .when()
                .post(PropertyUtil.getProperty("endpoint.createApplication"))
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/create-application-response-schema.json"))
                .extract()
                .as(CreateApplicationResponse.class);
    }

    @Step("Получение статуса заявки через API")
    public GetApplStatusResponse getApplStatus(Integer applicationId) {
        return RestAssured.given()
                .spec(RequestSpecs.authenticatedJsonSpec())
                .when()
                .get(PropertyUtil.getProperty("endpoint.getApplStatus") + "/" + applicationId)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get-appl-status-response-schema.json"))
                .extract()
                .as(GetApplStatusResponse.class);
    }
}

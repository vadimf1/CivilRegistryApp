package eu.senla.regoffice.api.client;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import eu.senla.regoffice.api.spec.RequestSpecs;
import eu.senla.regoffice.models.Admin;
import eu.senla.regoffice.models.ChangeApplicationStatusRequest;
import eu.senla.regoffice.models.ChangeApplicationStatusResponse;
import eu.senla.regoffice.models.CreateAdminResponse;
import eu.senla.regoffice.models.GetAllApplicationsResponse;
import eu.senla.regoffice.utils.PropertyUtil;

public class ApplicationAdministrationApiClient {
    @Step("Регистрация админа через API")
    public CreateAdminResponse registerAdmin(Admin admin) {
        return RestAssured
                .given()
                .spec(RequestSpecs.authenticatedJsonSpec())
                .body(admin)
                .when()
                .post(PropertyUtil.getProperty("endpoint.sendAdminRequest"))
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/register-admin-response-schema.json"))
                .extract()
                .as(CreateAdminResponse.class);
    }

    @Step("Получение всех заявок через API")
    public GetAllApplicationsResponse getAllApplications() {
        return RestAssured.given()
                .spec(RequestSpecs.authenticatedJsonSpec())
                .when()
                .get(PropertyUtil.getProperty("endpoint.getApplications"))
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get-all-applications-response-schema.json"))
                .extract()
                .as(GetAllApplicationsResponse.class);
    }

    @Step("Изменение статуса заявки через API")
    public ChangeApplicationStatusResponse changeApplicationStatus(ChangeApplicationStatusRequest request) {
        return RestAssured
                .given()
                .spec(RequestSpecs.authenticatedJsonSpec())
                .body(request)
                .when()
                .post(PropertyUtil.getProperty("endpoint.changeApplicationStatus"))
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/change-application-status-response-schema.json"))
                .extract()
                .as(ChangeApplicationStatusResponse.class);
    }
}

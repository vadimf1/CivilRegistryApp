package org.example.api.client;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.example.models.Admin;
import org.example.models.ChangeApplicationStatusRequest;
import org.example.models.ChangeApplicationStatusResponse;
import org.example.models.CreateAdminResponse;
import org.example.models.GetAllApplicationsResponse;
import org.example.utils.PropertyUtil;

import static org.example.api.spec.RequestSpecs.authenticatedJsonSpec;

public class ApplicationAdministrationApiClient {
    private final String REGISTER_ADMIN_ENDPOINT_KEY = "endpoint.sendAdminRequest";
    private final String GET_APPLICATIONS_ENDPOINT_KEY = "endpoint.getApplications";
    private final String CHANGE_APPLICATION_STATUS_ENDPOINT_KEY = "endpoint.changeApplicationStatus";

    @Step("Регистрация админа через API")
    public CreateAdminResponse registerAdmin(Admin admin) {
        return RestAssured
                .given()
                .spec(authenticatedJsonSpec())
                .body(admin)
                .when()
                .post(PropertyUtil.getProperty(REGISTER_ADMIN_ENDPOINT_KEY))
                .then()
                .statusCode(200)
                .extract()
                .as(CreateAdminResponse.class);
    }

    @Step("Получение всех заявок через API")
    public GetAllApplicationsResponse getAllApplications() {
        return RestAssured.given()
                .spec(authenticatedJsonSpec())
                .when()
                .get(PropertyUtil.getProperty(GET_APPLICATIONS_ENDPOINT_KEY))
                .then()
                .statusCode(200)
                .extract()
                .as(GetAllApplicationsResponse.class);
    }

    @Step("Изменение статуса заявки через API")
    public ChangeApplicationStatusResponse changeApplicationStatus(ChangeApplicationStatusRequest request) {
        return RestAssured
                .given()
                .spec(authenticatedJsonSpec())
                .body(request)
                .when()
                .post(PropertyUtil.getProperty(CHANGE_APPLICATION_STATUS_ENDPOINT_KEY))
                .then()
                .statusCode(200)
                .extract()
                .as(ChangeApplicationStatusResponse.class);
    }
}

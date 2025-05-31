package org.example.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.example.models.Admin;
import org.example.models.ChangeApplicationStatusRequest;
import org.example.models.ChangeApplicationStatusResponse;
import org.example.models.CreateAdminResponse;
import org.example.models.GetAllApplicationsResponse;

import static org.example.api.RequestSpecs.authenticatedJsonSpec;

public class ApplicationAdministrationApiClient {
    private final String REGISTER_ADMIN_URL = "https://regoffice.senla.eu/sendAdminRequest";
    private final String GET_APPLICATIONS_URL = "https://regoffice.senla.eu/getApplications";
    private final String CHANGE_APPLICATION_STATUS_URL = "https://regoffice.senla.eu/requestProcess\n";

    @Step("Регистрация админа через API")
    public CreateAdminResponse registerAdmin(Admin admin) {
        return RestAssured
                .given()
                .spec(authenticatedJsonSpec())
                .body(admin)
                .when()
                .post(REGISTER_ADMIN_URL)
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
                .get(GET_APPLICATIONS_URL)
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
                .post(CHANGE_APPLICATION_STATUS_URL)
                .then()
                .statusCode(200)
                .extract()
                .as(ChangeApplicationStatusResponse.class);
    }
}

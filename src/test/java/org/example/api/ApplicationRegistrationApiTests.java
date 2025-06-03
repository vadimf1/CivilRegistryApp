package org.example.api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.example.api.client.ApplicationRegistrationApiClient;
import org.example.db.ApplicationManager;
import org.example.models.CreateApplicationResponse;
import org.example.models.User;
import org.example.utils.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("API Тесты регистрации заявок")
@Feature("Подача заявки пользователем")
public class ApplicationRegistrationApiTests {
    ApplicationRegistrationApiClient applicationRegistrationApiClient;
    ApplicationManager applicationManager;

    @BeforeEach
    void setUp() {
        applicationRegistrationApiClient = new ApplicationRegistrationApiClient();
        applicationManager = new ApplicationManager();
    }

    @TmsLink("328")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация рождения) - Валидный запрос")
    void createBirthApplicationTest() {
        User user = UserFactory.createUserForBirthRegistration();
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(user);
        Assertions.assertTrue(applicationManager.applicationExistsById(response.getData().getApplicationId()));
    }

    @TmsLink("327")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация брака) - Валидный запрос")
    void createMarriageApplicationTest() {
        User user = UserFactory.createUserForMarriageRegistration();
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(user);
        Assertions.assertTrue(applicationManager.applicationExistsById(response.getData().getApplicationId()));
    }

    @TmsLink("330")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация смерти) - Валидный запрос")
    void createDeathApplicationTest() {
        User user = UserFactory.createUserForDeathRegistration();
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(user);
        Assertions.assertTrue(applicationManager.applicationExistsById(response.getData().getApplicationId()));
    }

    @TmsLink("353")
    @Test
    @DisplayName("GET /getApplStatus/{applicationId} - Получение статуса заявки - Валидный запрос")
    void getApplicationStatusTest() {
        User user = UserFactory.createUserForDeathRegistration();
        CreateApplicationResponse createApplicationResponse = applicationRegistrationApiClient.createApplication(user);
        Integer applicationId = createApplicationResponse.getData().getApplicationId();

        applicationRegistrationApiClient.getApplStatus(applicationId);
    }
}

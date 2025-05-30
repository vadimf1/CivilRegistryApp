package org.example.api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.example.models.ApplicationResponse;
import org.example.models.User;
import org.example.utils.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тесты регистрации заявок")
@Feature("Подача заявки пользователем")
public class ApplicationRegistrationApiTests {
    ApplicationRegistrationApiClient applicationRegistrationApiClient;

    @BeforeEach
    void setUp() {
        applicationRegistrationApiClient = new ApplicationRegistrationApiClient();
    }

    @TmsLink("328")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация рождения) - Валидный запрос")
    void createBirthApplicationTest() {
        User user = UserFactory.createUserForBirthRegistration();
        ApplicationResponse response = applicationRegistrationApiClient.createApplication(user);

        assertNotNull(response.getRequestId());
        assertTrue(response.getData().getApplicationId() > 0);
    }

    @TmsLink("327")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация брака) - Валидный запрос")
    void createMarriageApplicationTest() {
        User user = UserFactory.createUserForMarriageRegistration();
        ApplicationResponse response = applicationRegistrationApiClient.createApplication(user);

        assertNotNull(response.getRequestId());
        assertTrue(response.getData().getApplicationId() > 0);
    }

    @TmsLink("330")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация смерти) - Валидный запрос")
    void createDeathApplicationTest() {
        User user = UserFactory.createUserForDeathRegistration();
        ApplicationResponse response = applicationRegistrationApiClient.createApplication(user);

        assertNotNull(response.getRequestId());
        assertTrue(response.getData().getApplicationId() > 0);
    }
}

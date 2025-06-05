package eu.senla.regoffice.api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import eu.senla.regoffice.api.client.ApplicationRegistrationApiClient;
import eu.senla.regoffice.db.managers.ApplicationManager;
import eu.senla.regoffice.models.CreateApplicationResponse;
import eu.senla.regoffice.models.User;
import eu.senla.regoffice.factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("API Тесты регистрации заявок")
@Feature("Подача заявки пользователем")
public class ApplicationRegistrationApiTests extends BaseApiTest {
    ApplicationRegistrationApiClient applicationRegistrationApiClient;
    ApplicationManager applicationManager;

    @BeforeEach
    void setUp() {
        applicationRegistrationApiClient = new ApplicationRegistrationApiClient();
        applicationManager = new ApplicationManager(connection);
    }

    @TmsLink("328")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация рождения) - Валидный запрос")
    void createBirthApplicationTest() {
        User user = UserFactory.createUserForBirthRegistration();
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(user);
        Assertions.assertTrue(applicationManager.applicationExistsById(response.getData().getApplicationId()));
        applicationManager.deleteBirthApplicationById(response.getData().getApplicationId());
    }

    @TmsLink("327")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация брака) - Валидный запрос")
    void createMarriageApplicationTest() {
        User user = UserFactory.createUserForMarriageRegistration();
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(user);
        Assertions.assertTrue(applicationManager.applicationExistsById(response.getData().getApplicationId()));
        applicationManager.deleteMarriageApplicationById(response.getData().getApplicationId());
    }

    @TmsLink("330")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация смерти) - Валидный запрос")
    void createDeathApplicationTest() {
        User user = UserFactory.createUserForDeathRegistration();
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(user);
        Assertions.assertTrue(applicationManager.applicationExistsById(response.getData().getApplicationId()));
        applicationManager.deleteDeathApplicationById(response.getData().getApplicationId());
    }

    @TmsLink("353")
    @Test
    @DisplayName("GET /getApplStatus/{applicationId} - Получение статуса заявки - Валидный запрос")
    void getApplicationStatusTest() {
        User user = UserFactory.createUserForDeathRegistration();
        CreateApplicationResponse createApplicationResponse = applicationRegistrationApiClient.createApplication(user);
        Integer applicationId = createApplicationResponse.getData().getApplicationId();

        applicationRegistrationApiClient.getApplStatus(applicationId);
        applicationManager.deleteDeathApplicationById(createApplicationResponse.getData().getApplicationId());
    }
}

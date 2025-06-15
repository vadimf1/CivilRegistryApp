package eu.senla.regoffice.api;

import eu.senla.regoffice.db.service.DbService;
import eu.senla.regoffice.constants.ApplicationType;
import eu.senla.regoffice.models.GetApplStatusResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import eu.senla.regoffice.api.client.ApplicationRegistrationApiClient;
import eu.senla.regoffice.models.CreateApplicationResponse;
import eu.senla.regoffice.models.User;
import eu.senla.regoffice.factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("API")
@Epic("API Тесты регистрации заявок")
@Feature("Подача заявки пользователем")
public class ApplicationRegistrationApiTests extends BaseApiTest {
    ApplicationRegistrationApiClient applicationRegistrationApiClient;
    DbService dbService;
    
    @BeforeEach
    void setUp() {
        applicationRegistrationApiClient = new ApplicationRegistrationApiClient();
        dbService = new DbService(connection);
    }

    @TmsLink("328")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация рождения) - Валидный запрос")
    void createBirthApplicationTest() {
        User user = UserFactory.createUserForBirthRegistration();
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(user);
        Assertions.assertTrue(dbService.applicationExistsById(response.getData().getApplicationId()));
        dbService.deleteApplicationById(response.getData().getApplicationId(), ApplicationType.BIRTH);
    }

    @TmsLink("327")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация брака) - Валидный запрос")
    void createMarriageApplicationTest() {
        User user = UserFactory.createUserForMarriageRegistration();
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(user);
        Assertions.assertTrue(dbService.applicationExistsById(response.getData().getApplicationId()));
        dbService.deleteApplicationById(response.getData().getApplicationId(), ApplicationType.MARRIAGE);
    }

    @TmsLink("330")
    @Test
    @DisplayName("POST /sendUserRequest (Регистрация смерти) - Валидный запрос")
    void createDeathApplicationTest() {
        User user = UserFactory.createUserForDeathRegistration();
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(user);
        Assertions.assertTrue(dbService.applicationExistsById(response.getData().getApplicationId()));
        dbService.deleteApplicationById(response.getData().getApplicationId(), ApplicationType.DEATH);
    }

    @TmsLink("353")
    @Test
    @DisplayName("GET /getApplStatus/{applicationId} - Получение статуса заявки - Валидный запрос")
    void getApplicationStatusTest() {
        User user = UserFactory.createUserForDeathRegistration();
        CreateApplicationResponse createApplicationResponse = applicationRegistrationApiClient.createApplication(user);
        Integer applicationId = createApplicationResponse.getData().getApplicationId();
        GetApplStatusResponse getApplStatusResponse = applicationRegistrationApiClient.getApplStatus(applicationId);
        Assertions.assertEquals(dbService.getApplicationStatusById(applicationId), getApplStatusResponse.getData().getStatusOfApplication());
        dbService.deleteApplicationById(createApplicationResponse.getData().getApplicationId(), ApplicationType.DEATH);
    }
}

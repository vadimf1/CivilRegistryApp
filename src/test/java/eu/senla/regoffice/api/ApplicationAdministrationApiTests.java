package eu.senla.regoffice.api;

import eu.senla.regoffice.db.connection.ConnectionHolder;
import eu.senla.regoffice.db.service.DbService;
import eu.senla.regoffice.models.ApplicationType;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import eu.senla.regoffice.api.client.ApplicationAdministrationApiClient;
import eu.senla.regoffice.api.client.ApplicationRegistrationApiClient;
import eu.senla.regoffice.models.Admin;
import eu.senla.regoffice.models.ChangeApplicationStatusRequest;
import eu.senla.regoffice.models.ChangeApplicationStatusResponse;
import eu.senla.regoffice.models.CreateAdminResponse;
import eu.senla.regoffice.models.User;
import eu.senla.regoffice.factory.AdminFactory;
import eu.senla.regoffice.factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("API")
@Epic("API Администрирование")
@Feature("Работа с заявками")
public class ApplicationAdministrationApiTests extends BaseApiTest {
    ApplicationAdministrationApiClient applicationAdministrationApiClient;
    ApplicationRegistrationApiClient applicationRegistrationApiClient;
    DbService dbService;

    @BeforeEach
    void setUp() {
        applicationAdministrationApiClient = new ApplicationAdministrationApiClient();
        applicationRegistrationApiClient = new ApplicationRegistrationApiClient();
        dbService = new DbService(ConnectionHolder.getConnection());
    }

    @TmsLink("334")
    @Test
    @DisplayName("POST /sendAdminRequest - Создание админа - Валидный запрос")
    void registerAdminTest() {
        Admin admin = AdminFactory.createAdmin();
        CreateAdminResponse response = applicationAdministrationApiClient.registerAdmin(admin);
        Assertions.assertTrue(dbService.adminExistsById(response.getData().getStaffId()));
        dbService.deleteAdminById(response.getData().getStaffId());
    }

    @TmsLink("357")
    @Test
    @DisplayName("GET /getApplication - Получение информации о заявках - Валидный запрос")
    void getAllApplicationsTest() {
        applicationAdministrationApiClient.getAllApplications();
    }

    @TmsLink("336")
    @Test
    @DisplayName("POST /processRequest - Одобрение заявки (Регистрация рождения) - Валидный запрос")
    void approveBirthApplicationTest() {
        User user = UserFactory.createUserForBirthRegistration();
        int applicationId = dbService.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = dbService.createAdmin(admin);

        ChangeApplicationStatusResponse response = applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("approved")
                        .build()
        );
        Assertions.assertEquals(applicationId, response.getData().getApplicationId());
        Assertions.assertEquals("approved", response.getData().getStatusOfApplication());
        Assertions.assertEquals("approved", dbService.getApplicationStatusById(applicationId));
        dbService.deleteApplicationById(applicationId, ApplicationType.BIRTH);
        dbService.deleteAdminById(staffId);
    }

    @TmsLink("337")
    @Test
    @DisplayName("POST /processRequest - Одобрение заявки (Регистрация брака) - Валидный запрос")
    void approveMarriageApplicationTest() {
        User user = UserFactory.createUserForMarriageRegistration();
        int applicationId = dbService.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = dbService.createAdmin(admin);

        ChangeApplicationStatusResponse response = applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("approved")
                        .build()
        );
        Assertions.assertEquals(applicationId, response.getData().getApplicationId());
        Assertions.assertEquals("approved", response.getData().getStatusOfApplication());
        Assertions.assertEquals("approved", dbService.getApplicationStatusById(applicationId));
        dbService.deleteApplicationById(applicationId, ApplicationType.MARRIAGE);
        dbService.deleteAdminById(staffId);
    }

    @TmsLink("338")
    @Test
    @DisplayName("POST /processRequest - Одобрение заявки (Регистрация смерти) - Валидный запрос")
    void approveDeathApplicationTest() {
        User user = UserFactory.createUserForDeathRegistration();
        int applicationId = dbService.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = dbService.createAdmin(admin);

        ChangeApplicationStatusResponse response = applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("approved")
                        .build()
        );
        Assertions.assertEquals(applicationId, response.getData().getApplicationId());
        Assertions.assertEquals("approved", response.getData().getStatusOfApplication());
        Assertions.assertEquals("approved", dbService.getApplicationStatusById(applicationId));
        dbService.deleteApplicationById(applicationId, ApplicationType.DEATH);
        dbService.deleteAdminById(staffId);
    }

    @TmsLink("341")
    @Test
    @DisplayName("POST /processRequest - Отклонение заявки (Регистрация смерти) - Валидный запрос")
    void rejectDeathApplicationTest() {
        User user = UserFactory.createUserForDeathRegistration();
        int applicationId = dbService.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = dbService.createAdmin(admin);

        ChangeApplicationStatusResponse response = applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("rejected")
                        .build()
        );
        Assertions.assertEquals(applicationId, response.getData().getApplicationId());
        Assertions.assertEquals("rejected", response.getData().getStatusOfApplication());
        Assertions.assertEquals("rejected", dbService.getApplicationStatusById(applicationId));
        dbService.deleteApplicationById(applicationId, ApplicationType.DEATH);
        dbService.deleteAdminById(staffId);
    }

    @TmsLink("339")
    @Test
    @DisplayName("POST /processRequest - Отклонение заявки (Регистрация рождения) - Валидный запрос")
    void rejectBirthApplicationTest() {
        User user = UserFactory.createUserForBirthRegistration();
        int applicationId = dbService.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = dbService.createAdmin(admin);

        ChangeApplicationStatusResponse response = applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("rejected")
                        .build()
        );
        Assertions.assertEquals(applicationId, response.getData().getApplicationId());
        Assertions.assertEquals("rejected", response.getData().getStatusOfApplication());
        Assertions.assertEquals("rejected", dbService.getApplicationStatusById(applicationId));
        dbService.deleteApplicationById(applicationId, ApplicationType.BIRTH);
        dbService.deleteAdminById(staffId);
    }

    @TmsLink("340")
    @Test
    @DisplayName("POST /processRequest - Отклонение заявки (Регистрация брака) - Валидный запрос")
    void rejectMarriageApplicationTest() {
        User user = UserFactory.createUserForMarriageRegistration();
        int applicationId = dbService.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = dbService.createAdmin(admin);

        ChangeApplicationStatusResponse response = applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("rejected")
                        .build()
        );
        Assertions.assertEquals(applicationId, response.getData().getApplicationId());
        Assertions.assertEquals("rejected", response.getData().getStatusOfApplication());
        Assertions.assertEquals("rejected", dbService.getApplicationStatusById(applicationId));
        dbService.deleteApplicationById(applicationId, ApplicationType.MARRIAGE);
        dbService.deleteAdminById(staffId);
    }
}

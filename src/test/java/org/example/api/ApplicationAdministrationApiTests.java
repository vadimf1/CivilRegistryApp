package org.example.api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.example.api.client.ApplicationAdministrationApiClient;
import org.example.api.client.ApplicationRegistrationApiClient;
import org.example.models.Admin;
import org.example.models.ChangeApplicationStatusRequest;
import org.example.models.ChangeApplicationStatusResponse;
import org.example.models.CreateAdminResponse;
import org.example.models.CreateApplicationResponse;
import org.example.models.User;
import org.example.utils.AdminFactory;
import org.example.utils.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("API Администрирование")
@Feature("Работа с заявками")
public class ApplicationAdministrationApiTests {
    ApplicationAdministrationApiClient applicationAdministrationApiClient;
    ApplicationRegistrationApiClient applicationRegistrationApiClient;

    @BeforeEach
    void setUp() {
        applicationAdministrationApiClient = new ApplicationAdministrationApiClient();
        applicationRegistrationApiClient = new ApplicationRegistrationApiClient();
    }

    @TmsLink("334")
    @Test
    @DisplayName("POST /sendAdminRequest - Создание админа - Валидный запрос")
    void registerAdminTest() {
        Admin admin = AdminFactory.createAdmin();
        applicationAdministrationApiClient.registerAdmin(admin);
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
        CreateApplicationResponse createApplicationResponse = applicationRegistrationApiClient.createApplication(user);
        Integer applicationId = createApplicationResponse.getData().getApplicationId();

        Admin admin = AdminFactory.createAdmin();
        CreateAdminResponse createAdminResponse = applicationAdministrationApiClient.registerAdmin(admin);
        Integer staffId = createAdminResponse.getData().getStaffId();

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("approved")
                        .build()
        );
    }

    @TmsLink("337")
    @Test
    @DisplayName("POST /processRequest - Одобрение заявки (Регистрация брака) - Валидный запрос")
    void approveMarriageApplicationTest() {
        User user = UserFactory.createUserForMarriageRegistration();
        CreateApplicationResponse createApplicationResponse = applicationRegistrationApiClient.createApplication(user);
        Integer applicationId = createApplicationResponse.getData().getApplicationId();

        Admin admin = AdminFactory.createAdmin();
        CreateAdminResponse createAdminResponse = applicationAdministrationApiClient.registerAdmin(admin);
        Integer staffId = createAdminResponse.getData().getStaffId();

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("approved")
                        .build()
        );
    }

    @TmsLink("338")
    @Test
    @DisplayName("POST /processRequest - Одобрение заявки (Регистрация смерти) - Валидный запрос")
    void approveDeathApplicationTest() {
        User user = UserFactory.createUserForDeathRegistration();
        CreateApplicationResponse createApplicationResponse = applicationRegistrationApiClient.createApplication(user);
        Integer applicationId = createApplicationResponse.getData().getApplicationId();

        Admin admin = AdminFactory.createAdmin();
        CreateAdminResponse createAdminResponse = applicationAdministrationApiClient.registerAdmin(admin);
        Integer staffId = createAdminResponse.getData().getStaffId();

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("approved")
                        .build()
        );
    }

    @TmsLink("341")
    @Test
    @DisplayName("POST /processRequest - Отклонение заявки (Регистрация смерти) - Валидный запрос")
    void rejectDeathApplicationTest() {
        User user = UserFactory.createUserForDeathRegistration();
        CreateApplicationResponse createApplicationResponse = applicationRegistrationApiClient.createApplication(user);
        Integer applicationId = createApplicationResponse.getData().getApplicationId();

        Admin admin = AdminFactory.createAdmin();
        CreateAdminResponse createAdminResponse = applicationAdministrationApiClient.registerAdmin(admin);
        Integer staffId = createAdminResponse.getData().getStaffId();

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("rejected")
                        .build()
        );
    }

    @TmsLink("339")
    @Test
    @DisplayName("POST /processRequest - Отклонение заявки (Регистрация рождения) - Валидный запрос")
    void rejectBirthApplicationTest() {
        User user = UserFactory.createUserForBirthRegistration();
        CreateApplicationResponse createApplicationResponse = applicationRegistrationApiClient.createApplication(user);
        Integer applicationId = createApplicationResponse.getData().getApplicationId();

        Admin admin = AdminFactory.createAdmin();
        CreateAdminResponse createAdminResponse = applicationAdministrationApiClient.registerAdmin(admin);
        Integer staffId = createAdminResponse.getData().getStaffId();

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("rejected")
                        .build()
        );
    }

    @TmsLink("340")
    @Test
    @DisplayName("POST /processRequest - Отклонение заявки (Регистрация брака) - Валидный запрос")
    void rejectMarriageApplicationTest() {
        User user = UserFactory.createUserForMarriageRegistration();
        CreateApplicationResponse createApplicationResponse = applicationRegistrationApiClient.createApplication(user);
        Integer applicationId = createApplicationResponse.getData().getApplicationId();

        Admin admin = AdminFactory.createAdmin();
        CreateAdminResponse createAdminResponse = applicationAdministrationApiClient.registerAdmin(admin);
        Integer staffId = createAdminResponse.getData().getStaffId();

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("rejected")
                        .build()
        );
    }
}

package org.example.api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.example.api.client.ApplicationAdministrationApiClient;
import org.example.api.client.ApplicationRegistrationApiClient;
import org.example.db.AdminManager;
import org.example.db.ApplicationManager;
import org.example.models.Admin;
import org.example.models.ChangeApplicationStatusRequest;
import org.example.models.CreateAdminResponse;
import org.example.models.User;
import org.example.utils.AdminFactory;
import org.example.utils.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("API Администрирование")
@Feature("Работа с заявками")
public class ApplicationAdministrationApiTests {
    ApplicationAdministrationApiClient applicationAdministrationApiClient;
    ApplicationRegistrationApiClient applicationRegistrationApiClient;
    ApplicationManager applicationManager;
    AdminManager adminManager;

    @BeforeEach
    void setUp() {
        applicationAdministrationApiClient = new ApplicationAdministrationApiClient();
        applicationRegistrationApiClient = new ApplicationRegistrationApiClient();
        applicationManager = new ApplicationManager();
        adminManager = new AdminManager();
    }

    @TmsLink("334")
    @Test
    @DisplayName("POST /sendAdminRequest - Создание админа - Валидный запрос")
    void registerAdminTest() {
        Admin admin = AdminFactory.createAdmin();
        CreateAdminResponse response = applicationAdministrationApiClient.registerAdmin(admin);
        Assertions.assertTrue(adminManager.adminExistsById(response.getData().getStaffId()));
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
        int applicationId = applicationManager.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = adminManager.createAdmin(admin);

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("approved")
                        .build()
        );
        Assertions.assertEquals("approved", applicationManager.getApplicationStatusById(applicationId));
    }

    @TmsLink("337")
    @Test
    @DisplayName("POST /processRequest - Одобрение заявки (Регистрация брака) - Валидный запрос")
    void approveMarriageApplicationTest() {
        User user = UserFactory.createUserForMarriageRegistration();
        int applicationId = applicationManager.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = adminManager.createAdmin(admin);

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("approved")
                        .build()
        );
        Assertions.assertEquals("approved", applicationManager.getApplicationStatusById(applicationId));
    }

    @TmsLink("338")
    @Test
    @DisplayName("POST /processRequest - Одобрение заявки (Регистрация смерти) - Валидный запрос")
    void approveDeathApplicationTest() {
        User user = UserFactory.createUserForDeathRegistration();
        int applicationId = applicationManager.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = adminManager.createAdmin(admin);

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("approved")
                        .build()
        );
        Assertions.assertEquals("approved", applicationManager.getApplicationStatusById(applicationId));
    }

    @TmsLink("341")
    @Test
    @DisplayName("POST /processRequest - Отклонение заявки (Регистрация смерти) - Валидный запрос")
    void rejectDeathApplicationTest() {
        User user = UserFactory.createUserForDeathRegistration();
        int applicationId = applicationManager.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = adminManager.createAdmin(admin);

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("rejected")
                        .build()
        );
        Assertions.assertEquals("rejected", applicationManager.getApplicationStatusById(applicationId));
    }

    @TmsLink("339")
    @Test
    @DisplayName("POST /processRequest - Отклонение заявки (Регистрация рождения) - Валидный запрос")
    void rejectBirthApplicationTest() {
        User user = UserFactory.createUserForBirthRegistration();
        int applicationId = applicationManager.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = adminManager.createAdmin(admin);

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("rejected")
                        .build()
        );
        Assertions.assertEquals("rejected", applicationManager.getApplicationStatusById(applicationId));
    }

    @TmsLink("340")
    @Test
    @DisplayName("POST /processRequest - Отклонение заявки (Регистрация брака) - Валидный запрос")
    void rejectMarriageApplicationTest() {
        User user = UserFactory.createUserForMarriageRegistration();
        int applicationId = applicationManager.createApplication(user);

        Admin admin = AdminFactory.createAdmin();
        int staffId = adminManager.createAdmin(admin);

        applicationAdministrationApiClient.changeApplicationStatus(
                ChangeApplicationStatusRequest.builder()
                        .appId(applicationId)
                        .staffId(staffId)
                        .action("rejected")
                        .build()
        );
        Assertions.assertEquals("rejected", applicationManager.getApplicationStatusById(applicationId));
    }
}

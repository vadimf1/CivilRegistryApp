package org.example.ui;

import io.qameta.allure.*;
import org.example.api.ApplicationRegistrationApiClient;
import org.example.models.Admin;
import org.example.models.CreateApplicationResponse;
import org.example.ui.pages.AdminRegistrationDataPage;
import org.example.ui.pages.ApplicationAdministrationPage;
import org.example.utils.AdminFactory;
import org.example.utils.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Администрирование")
@Feature("Работа с заявками")
@Severity(SeverityLevel.CRITICAL)
public class ApplicationAdministrationTests extends BaseUiTest {

    AdminRegistrationDataPage adminRegistrationDataPage;
    ApplicationAdministrationPage applicationAdministrationPage;
    ApplicationRegistrationApiClient applicationRegistrationApiClient;

    @BeforeEach
    void setUp() {
        adminRegistrationDataPage = new AdminRegistrationDataPage();
        applicationAdministrationPage = new ApplicationAdministrationPage();
        applicationRegistrationApiClient = new ApplicationRegistrationApiClient();
    }

    @TmsLink("157")
    @Test
    @DisplayName("Администрирование заявок: статус 'Одобрена' - E2E")
    void approveApplicationTest() {
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(UserFactory.createUserForBirthRegistration());
        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.approveApplicationByNumber(String.valueOf(response.getData().getApplicationId()));
        applicationAdministrationPage.checkApplicationIsApproved(String.valueOf(response.getData().getApplicationId()));
    }

    @TmsLink("157")
    @Test
    @DisplayName("Администрирование заявок: статус 'Отклонена' - E2E")
    void rejectApplicationTest() {
        CreateApplicationResponse response = applicationRegistrationApiClient.createApplication(UserFactory.createUserForBirthRegistration());
        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.rejectApplicationByNumber(String.valueOf(response.getData().getApplicationId()));
        applicationAdministrationPage.checkApplicationIsRejected(String.valueOf(response.getData().getApplicationId()));
    }
}

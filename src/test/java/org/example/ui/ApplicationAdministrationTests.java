package org.example.ui;

import io.qameta.allure.*;
import org.example.db.ApplicationManager;
import org.example.models.Admin;
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
    ApplicationManager applicationManager;

    @BeforeEach
    void setUp() {
        adminRegistrationDataPage = new AdminRegistrationDataPage();
        applicationAdministrationPage = new ApplicationAdministrationPage();
        applicationManager = new ApplicationManager();
    }

    @TmsLink("157")
    @Test
    @DisplayName("Администрирование заявок: статус 'Одобрена' - E2E")
    void approveApplicationTest() {
        int applicationId = applicationManager.createApplication(UserFactory.createUserForBirthRegistration());
        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.approveApplicationByNumber(String.valueOf(applicationId));
        applicationAdministrationPage.checkApplicationIsApproved(String.valueOf(applicationId));
    }

    @TmsLink("157")
    @Test
    @DisplayName("Администрирование заявок: статус 'Отклонена' - E2E")
    void rejectApplicationTest() {
        int applicationId = applicationManager.createApplication(UserFactory.createUserForBirthRegistration());
        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.rejectApplicationByNumber(String.valueOf(applicationId));
        applicationAdministrationPage.checkApplicationIsRejected(String.valueOf(applicationId));
    }
}

package org.example;

import io.qameta.allure.*;
import org.example.models.Admin;
import org.example.ui.pages.AdminRegistrationDataPage;
import org.example.ui.pages.ApplicationAdministrationPage;
import org.example.utils.AdminFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Администрирование")
@Feature("Работа с заявками")
@Severity(SeverityLevel.CRITICAL)
public class ApplicationAdministrationTests extends BaseUiTest {

    AdminRegistrationDataPage adminRegistrationDataPage;
    ApplicationAdministrationPage applicationAdministrationPage;

    @BeforeEach
    void setUp() {
        adminRegistrationDataPage = new AdminRegistrationDataPage();
        applicationAdministrationPage = new ApplicationAdministrationPage();
    }

    @TmsLink("157")
    @Test
    @DisplayName("Администрирование заявок: статус 'Одобрена' - E2E")
    void approveApplicationTest() {
        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        String applicationNumber = applicationAdministrationPage.getRandomApplicationNumber();
        applicationAdministrationPage.approveApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsApproved(applicationNumber);
    }

    @TmsLink("157")
    @Test
    @DisplayName("Администрирование заявок: статус 'Отклонена' - E2E")
    void rejectApplicationTest() {
        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        String applicationNumber = applicationAdministrationPage.getRandomApplicationNumber();
        applicationAdministrationPage.rejectApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsRejected(applicationNumber);
    }
}

package eu.senla.regoffice.ui;

import io.qameta.allure.*;
import eu.senla.regoffice.db.managers.AdminManager;
import eu.senla.regoffice.db.managers.ApplicationManager;
import eu.senla.regoffice.models.Admin;
import eu.senla.regoffice.ui.pages.AdminRegistrationDataPage;
import eu.senla.regoffice.ui.pages.ApplicationAdministrationPage;
import eu.senla.regoffice.factory.AdminFactory;
import eu.senla.regoffice.factory.UserFactory;
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
    AdminManager adminManager;

    @BeforeEach
    void setUp() {
        adminRegistrationDataPage = new AdminRegistrationDataPage();
        applicationAdministrationPage = new ApplicationAdministrationPage();
        applicationManager = new ApplicationManager(connection);
        adminManager = new AdminManager(connection);
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
        applicationManager.deleteBirthApplicationById(applicationId);
        adminManager.deleteAdminById(adminManager.getLastAdminId());
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
        applicationManager.deleteBirthApplicationById(applicationId);
        adminManager.deleteAdminById(adminManager.getLastAdminId());
    }
}

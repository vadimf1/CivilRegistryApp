package eu.senla.regoffice.ui;

import eu.senla.regoffice.db.service.DbService;
import eu.senla.regoffice.models.Admin;
import eu.senla.regoffice.models.ApplicationType;
import eu.senla.regoffice.ui.pages.AdminRegistrationDataPage;
import eu.senla.regoffice.ui.pages.ApplicationAdministrationPage;
import eu.senla.regoffice.factory.AdminFactory;
import eu.senla.regoffice.factory.UserFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UI")
@Epic("Администрирование")
@Feature("Работа с заявками")
@Severity(SeverityLevel.CRITICAL)
public class ApplicationAdministrationTests extends BaseUiTest {

    AdminRegistrationDataPage adminRegistrationDataPage;
    ApplicationAdministrationPage applicationAdministrationPage;
    DbService dbService;

    @BeforeEach
    void setUp() {
        adminRegistrationDataPage = new AdminRegistrationDataPage();
        applicationAdministrationPage = new ApplicationAdministrationPage();
        dbService = new DbService(connection);
    }

    @TmsLink("157")
    @Test
    @DisplayName("Администрирование заявок: статус 'Одобрена' - E2E")
    void approveApplicationTest() {
        int applicationId = dbService.createApplication(UserFactory.createUserForBirthRegistration());
        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.approveApplicationByNumber(String.valueOf(applicationId));
        applicationAdministrationPage.checkApplicationIsApproved(String.valueOf(applicationId));
        dbService.deleteApplicationById(applicationId, ApplicationType.BIRTH);
        dbService.deleteAdminById(dbService.getLastAdminId());
    }

    @TmsLink("157")
    @Test
    @DisplayName("Администрирование заявок: статус 'Отклонена' - E2E")
    void rejectApplicationTest() {
        int applicationId = dbService.createApplication(UserFactory.createUserForBirthRegistration());
        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.rejectApplicationByNumber(String.valueOf(applicationId));
        applicationAdministrationPage.checkApplicationIsRejected(String.valueOf(applicationId));
        dbService.deleteApplicationById(applicationId, ApplicationType.BIRTH);
        dbService.deleteAdminById(dbService.getLastAdminId());
    }
}

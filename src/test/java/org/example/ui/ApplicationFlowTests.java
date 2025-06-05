package org.example.ui;

import io.qameta.allure.*;
import org.example.db.managers.AdminManager;
import org.example.db.managers.ApplicationManager;
import org.example.models.Admin;
import org.example.models.User;
import org.example.ui.pages.*;
import org.example.utils.factory.AdminFactory;
import org.example.utils.factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("E2E тесты регистрации и администрирования")
@Feature("Подача заявок и управление их статусами")
public class ApplicationFlowTests extends BaseUiTest {
    ApplicantDataPage applicantDataPage;
    ServiceSelectionPage serviceSelectionPage;
    CitizenDataPage citizenDataPage;
    ApplicationStatusPage applicationStatusPage;
    AdminRegistrationDataPage adminRegistrationDataPage;
    ApplicationAdministrationPage applicationAdministrationPage;
    ApplicationManager applicationManager;
    AdminManager adminManager;

    @BeforeEach
    void setUp() {
        applicantDataPage = new ApplicantDataPage();
        serviceSelectionPage = new ServiceSelectionPage();
        citizenDataPage = new CitizenDataPage();
        applicationStatusPage = new ApplicationStatusPage();
        adminRegistrationDataPage = new AdminRegistrationDataPage();
        applicationAdministrationPage = new ApplicationAdministrationPage();
        applicationManager = new ApplicationManager(connection);
        adminManager = new AdminManager(connection);
    }

    @TmsLink("367")
    @Test
    @DisplayName("Регистрация смерти с изменением статуса на 'Одобрено' - E2E")
    @Severity(SeverityLevel.CRITICAL)
    void deathApplicationApproveFlow() {
        User user = UserFactory.createUserForDeathRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        action.clickNext();
        serviceSelectionPage.chooseDeathRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        action.clickNext();
        DeathRegistrationDataPage deathRegistrationDataPage = new DeathRegistrationDataPage();
        deathRegistrationDataPage.fillDeathRegistrationDataForm(user);
        action.clickFinish();
        applicationStatusPage.checkIsLoaded();
        action.clickClose();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.approveApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsApproved(applicationNumber);
        action.clickClose();

        mainPage.enterAsUser();
        action.clickRefresh();
        applicationStatusPage.checkApplicationIsApproved();
        applicationManager.deleteDeathApplicationById(applicationManager.getLastApplicationId());
        adminManager.deleteAdminById(adminManager.getLastAdminId());
    }

    @TmsLink("368")
    @Test
    @DisplayName("Регистрация смерти с изменением статуса на 'Отклонено' - E2E")
    @Severity(SeverityLevel.CRITICAL)
    void deathApplicationRejectFlow() {
        User user = UserFactory.createUserForDeathRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        action.clickNext();
        serviceSelectionPage.chooseDeathRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        action.clickNext();
        DeathRegistrationDataPage deathRegistrationDataPage = new DeathRegistrationDataPage();
        deathRegistrationDataPage.fillDeathRegistrationDataForm(user);
        action.clickFinish();
        applicationStatusPage.checkIsLoaded();
        action.clickClose();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.rejectApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsRejected(applicationNumber);
        action.clickClose();

        mainPage.enterAsUser();
        action.clickRefresh();
        applicationStatusPage.checkApplicationIsRejected();
        applicationManager.deleteDeathApplicationById(applicationManager.getLastApplicationId());
        adminManager.deleteAdminById(adminManager.getLastAdminId());
    }

    @TmsLink("361")
    @Test
    @DisplayName("Регистрация рождения с изменением статуса на 'Одобрено' - E2E")
    @Severity(SeverityLevel.CRITICAL)
    void birthApplicationApproveFlow() {
        User user = UserFactory.createUserForBirthRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        action.clickNext();
        serviceSelectionPage.chooseBirthRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        action.clickNext();
        BirthRegistrationDataPage birthRegistrationDataPage = new BirthRegistrationDataPage();
        birthRegistrationDataPage.fillBirthRegistrationDataFrom(user);
        action.clickFinish();
        applicationStatusPage.checkIsLoaded();
        action.clickClose();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.approveApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsApproved(applicationNumber);
        action.clickClose();

        mainPage.enterAsUser();
        action.clickRefresh();
        applicationStatusPage.checkApplicationIsApproved();
        applicationManager.deleteBirthApplicationById(applicationManager.getLastApplicationId());
        adminManager.deleteAdminById(adminManager.getLastAdminId());
    }

    @TmsLink("362")
    @Test
    @DisplayName("Регистрация рождения с изменением статуса на 'Отклонено' - E2E")
    @Severity(SeverityLevel.CRITICAL)
    void birthApplicationRejectFlow() {
        User user = UserFactory.createUserForBirthRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        action.clickNext();
        serviceSelectionPage.chooseBirthRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        action.clickNext();
        BirthRegistrationDataPage birthRegistrationDataPage = new BirthRegistrationDataPage();
        birthRegistrationDataPage.fillBirthRegistrationDataFrom(user);
        action.clickFinish();
        applicationStatusPage.checkIsLoaded();
        action.clickClose();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.rejectApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsRejected(applicationNumber);
        action.clickClose();

        mainPage.enterAsUser();
        action.clickRefresh();
        applicationStatusPage.checkApplicationIsRejected();
        applicationManager.deleteBirthApplicationById(applicationManager.getLastApplicationId());
        adminManager.deleteAdminById(adminManager.getLastAdminId());
    }

    @TmsLink("365")
    @Test
    @DisplayName("Регистрация брака с изменением статуса на 'Одобрено' - E2E")
    @Severity(SeverityLevel.CRITICAL)
    void marriageApplicationApproveFlow() {
        User user = UserFactory.createUserForMarriageRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        action.clickNext();
        serviceSelectionPage.chooseMarriageRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        action.clickNext();
        MarriageRegistrationDataPage marriageRegistrationDataPage = new MarriageRegistrationDataPage();
        marriageRegistrationDataPage.fillMarriageRegistrationDataForm(user);
        action.clickFinish();
        applicationStatusPage.checkIsLoaded();
        action.clickClose();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.approveApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsApproved(applicationNumber);
        action.clickClose();

        mainPage.enterAsUser();
        action.clickRefresh();
        applicationStatusPage.checkApplicationIsApproved();
        applicationManager.deleteMarriageApplicationById(applicationManager.getLastApplicationId());
        adminManager.deleteAdminById(adminManager.getLastAdminId());
    }

    @TmsLink("366")
    @Test
    @DisplayName("Регистрация брака с изменением статуса на 'Отклонено' - E2E")
    @Severity(SeverityLevel.CRITICAL)
    void marriageApplicationRejectFlow() {
        User user = UserFactory.createUserForMarriageRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        action.clickNext();
        serviceSelectionPage.chooseMarriageRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        action.clickNext();
        MarriageRegistrationDataPage marriageRegistrationDataPage = new MarriageRegistrationDataPage();
        marriageRegistrationDataPage.fillMarriageRegistrationDataForm(user);
        action.clickFinish();
        applicationStatusPage.checkIsLoaded();
        action.clickClose();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        action.clickNext();
        applicationAdministrationPage.rejectApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsRejected(applicationNumber);
        action.clickClose();

        mainPage.enterAsUser();
        action.clickRefresh();
        applicationStatusPage.checkApplicationIsRejected();
        applicationManager.deleteMarriageApplicationById(applicationManager.getLastApplicationId());
        adminManager.deleteAdminById(adminManager.getLastAdminId());
    }
}

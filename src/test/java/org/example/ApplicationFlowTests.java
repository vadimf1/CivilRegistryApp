package org.example;

import io.qameta.allure.*;
import org.example.models.Admin;
import org.example.models.User;
import org.example.ui.pages.*;
import org.example.utils.AdminFactory;
import org.example.utils.UserFactory;
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

    @BeforeEach
    void setUp() {
        applicantDataPage = new ApplicantDataPage();
        serviceSelectionPage = new ServiceSelectionPage();
        citizenDataPage = new CitizenDataPage();
        applicationStatusPage = new ApplicationStatusPage();
        adminRegistrationDataPage = new AdminRegistrationDataPage();
        applicationAdministrationPage = new ApplicationAdministrationPage();
    }

    @Test
    @Story("Регистрация смерти и одобрение заявки")
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
    }

    @Test
    @Story("Регистрация смерти и отклонение заявки")
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
    }

    @Test
    @Story("Регистрация рождения и одобрение заявки")
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
    }

    @Test
    @Story("Регистрация рождения и отклонение заявки")
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
    }

    @Test
    @Story("Регистрация брака и одобрение заявки")
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
    }

    @Test
    @Story("Регистрация брака и отклонение заявки")
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
    }
}

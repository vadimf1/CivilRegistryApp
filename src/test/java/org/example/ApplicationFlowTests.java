package org.example;

import org.example.ui.models.Admin;
import org.example.ui.models.User;
import org.example.ui.pages.AdminRegistrationDataPage;
import org.example.ui.pages.ApplicantDataPage;
import org.example.ui.pages.ApplicationAdministrationPage;
import org.example.ui.pages.ApplicationStatusPage;
import org.example.ui.pages.BirthRegistrationDataPage;
import org.example.ui.pages.CitizenDataPage;
import org.example.ui.pages.DeathRegistrationDataPage;
import org.example.ui.pages.MarriageRegistrationDataPage;
import org.example.ui.pages.ServiceSelectionPage;
import org.example.ui.utils.AdminFactory;
import org.example.ui.utils.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("Register and approve death application")
    void deathApplicationApproveFlow() {
        User user = UserFactory.createUserForDeathRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        applicantDataPage.clickNextButton();
        serviceSelectionPage.chooseDeathRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        citizenDataPage.clickNextButton();
        DeathRegistrationDataPage deathRegistrationDataPage = new DeathRegistrationDataPage();
        deathRegistrationDataPage.fillDeathRegistrationDataForm(user);
        deathRegistrationDataPage.clickFinishButton();
        applicationStatusPage.checkIsLoaded();
        applicationStatusPage.clickCloseButton();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        adminRegistrationDataPage.clickNextButton();
        applicationAdministrationPage.approveApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsApproved(applicationNumber);
        applicationAdministrationPage.clickCloseButton();

        mainPage.enterAsUser();
        applicationStatusPage.clickRefreshButton();
        applicationStatusPage.checkApplicationIsApproved();
    }

    @Test
    @DisplayName("Register and reject death application")
    void deathApplicationRejectFlow() {
        User user = UserFactory.createUserForDeathRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        applicantDataPage.clickNextButton();
        serviceSelectionPage.chooseDeathRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        citizenDataPage.clickNextButton();
        DeathRegistrationDataPage deathRegistrationDataPage = new DeathRegistrationDataPage();
        deathRegistrationDataPage.fillDeathRegistrationDataForm(user);
        deathRegistrationDataPage.clickFinishButton();
        applicationStatusPage.checkIsLoaded();
        applicationStatusPage.clickCloseButton();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        adminRegistrationDataPage.clickNextButton();
        applicationAdministrationPage.rejectApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsRejected(applicationNumber);
        applicationAdministrationPage.clickCloseButton();

        mainPage.enterAsUser();
        applicationStatusPage.clickRefreshButton();
        applicationStatusPage.checkApplicationIsRejected();
    }

    @Test
    @DisplayName("Register and approve birth application")
    void birthApplicationApproveFlow() {
        User user = UserFactory.createUserForBirthRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        applicantDataPage.clickNextButton();
        serviceSelectionPage.chooseBirthRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        citizenDataPage.clickNextButton();
        BirthRegistrationDataPage birthRegistrationDataPage = new BirthRegistrationDataPage();
        birthRegistrationDataPage.fillBirthRegistrationDataFrom(user);
        birthRegistrationDataPage.clickFinishButton();
        applicationStatusPage.checkIsLoaded();
        applicationStatusPage.clickCloseButton();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        adminRegistrationDataPage.clickNextButton();
        applicationAdministrationPage.approveApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsApproved(applicationNumber);
        applicationAdministrationPage.clickCloseButton();

        mainPage.enterAsUser();
        applicationStatusPage.clickRefreshButton();
        applicationStatusPage.checkApplicationIsApproved();
    }

    @Test
    @DisplayName("Register and reject birth application")
    void birthApplicationRejectFlow() {
        User user = UserFactory.createUserForBirthRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        applicantDataPage.clickNextButton();
        serviceSelectionPage.chooseBirthRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        citizenDataPage.clickNextButton();
        BirthRegistrationDataPage birthRegistrationDataPage = new BirthRegistrationDataPage();
        birthRegistrationDataPage.fillBirthRegistrationDataFrom(user);
        birthRegistrationDataPage.clickFinishButton();
        applicationStatusPage.checkIsLoaded();
        applicationStatusPage.clickCloseButton();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        adminRegistrationDataPage.clickNextButton();
        applicationAdministrationPage.rejectApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsRejected(applicationNumber);
        applicationAdministrationPage.clickCloseButton();

        mainPage.enterAsUser();
        applicationStatusPage.clickRefreshButton();
        applicationStatusPage.checkApplicationIsRejected();
    }

    @Test
    @DisplayName("Register and approve marriage application")
    void marriageApplicationApproveFlow() {
        User user = UserFactory.createUserForMarriageRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        applicantDataPage.clickNextButton();
        serviceSelectionPage.chooseMarriageRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        citizenDataPage.clickNextButton();
        MarriageRegistrationDataPage marriageRegistrationDataPage = new MarriageRegistrationDataPage();
        marriageRegistrationDataPage.fillMarriageRegistrationDataForm(user);
        marriageRegistrationDataPage.clickFinishButton();
        applicationStatusPage.checkIsLoaded();
        applicationStatusPage.clickCloseButton();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        adminRegistrationDataPage.clickNextButton();
        applicationAdministrationPage.approveApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsApproved(applicationNumber);
        applicationAdministrationPage.clickCloseButton();

        mainPage.enterAsUser();
        applicationStatusPage.clickRefreshButton();
        applicationStatusPage.checkApplicationIsApproved();
    }

    @Test
    @DisplayName("Register and reject marriage application")
    void marriageApplicationRejectFlow() {
        User user = UserFactory.createUserForMarriageRegistration();
        mainPage.enterAsUser();
        applicantDataPage.fillApplicantDataForm(user);
        applicantDataPage.clickNextButton();
        serviceSelectionPage.chooseMarriageRegistration();
        citizenDataPage.fillCitizenDataForm(user);
        citizenDataPage.clickNextButton();
        MarriageRegistrationDataPage marriageRegistrationDataPage = new MarriageRegistrationDataPage();
        marriageRegistrationDataPage.fillMarriageRegistrationDataForm(user);
        marriageRegistrationDataPage.clickFinishButton();
        applicationStatusPage.checkIsLoaded();
        applicationStatusPage.clickCloseButton();
        String applicationNumber = applicationStatusPage.getApplicationNumber();

        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        adminRegistrationDataPage.clickNextButton();
        applicationAdministrationPage.rejectApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsRejected(applicationNumber);
        applicationAdministrationPage.clickCloseButton();

        mainPage.enterAsUser();
        applicationStatusPage.clickRefreshButton();
        applicationStatusPage.checkApplicationIsRejected();
    }
}

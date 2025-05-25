package org.example;

import org.example.ui.models.Admin;
import org.example.ui.pages.AdminRegistrationDataPage;
import org.example.ui.pages.ApplicationAdministrationPage;
import org.example.ui.utils.AdminFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApplicationAdministrationTests extends BaseUiTest {
    AdminRegistrationDataPage adminRegistrationDataPage;
    ApplicationAdministrationPage applicationAdministrationPage;

    @BeforeEach
    void setUp() {
        adminRegistrationDataPage = new AdminRegistrationDataPage();
        applicationAdministrationPage = new ApplicationAdministrationPage();
    }

    @Test
    @DisplayName("Approve application")
    void approveApplicationTest() {
        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        adminRegistrationDataPage.clickNextButton();
        String applicationNumber = applicationAdministrationPage.getRandomApplicationNumber();
        applicationAdministrationPage.approveApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsApproved(applicationNumber);
    }

    @Test
    @DisplayName("Reject application")
    void rejectApplicationTest() {
        Admin admin = AdminFactory.createAdmin();
        mainPage.enterAsAdmin();
        adminRegistrationDataPage.fillAdminRegistrationDataForm(admin);
        adminRegistrationDataPage.clickNextButton();
        String applicationNumber = applicationAdministrationPage.getRandomApplicationNumber();
        applicationAdministrationPage.rejectApplicationByNumber(applicationNumber);
        applicationAdministrationPage.checkApplicationIsRejected(applicationNumber);
    }
}

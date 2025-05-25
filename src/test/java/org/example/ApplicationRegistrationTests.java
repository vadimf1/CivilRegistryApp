package org.example;

import org.example.ui.models.User;
import org.example.ui.pages.ApplicantDataPage;
import org.example.ui.pages.ApplicationStatusPage;
import org.example.ui.pages.BirthRegistrationDataPage;
import org.example.ui.pages.CitizenDataPage;
import org.example.ui.pages.DeathRegistrationDataPage;
import org.example.ui.pages.MarriageRegistrationDataPage;
import org.example.ui.pages.ServiceSelectionPage;
import org.example.ui.utils.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApplicationRegistrationTests extends BaseUiTest {
    ApplicantDataPage applicantDataPage;
    ServiceSelectionPage serviceSelectionPage;
    CitizenDataPage citizenDataPage;
    ApplicationStatusPage applicationStatusPage;

    @BeforeEach
    void setUp() {
        applicantDataPage = new ApplicantDataPage();
        serviceSelectionPage = new ServiceSelectionPage();
        citizenDataPage = new CitizenDataPage();
        applicationStatusPage = new ApplicationStatusPage();
    }

    @Test
    @DisplayName("Death registration test")
    void deathRegistrationTest() {
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
    }

    @Test
    @DisplayName("Birth registration test")
    void birthRegistrationTest() {
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
    }

    @Test
    @DisplayName("Marriage registration test")
    void marriageRegistrationTest() {
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
    }
}

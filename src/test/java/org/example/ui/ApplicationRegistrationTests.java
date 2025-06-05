package org.example.ui;

import io.qameta.allure.*;
import org.example.models.User;
import org.example.ui.pages.ApplicantDataPage;
import org.example.ui.pages.ApplicationStatusPage;
import org.example.ui.pages.BirthRegistrationDataPage;
import org.example.ui.pages.CitizenDataPage;
import org.example.ui.pages.DeathRegistrationDataPage;
import org.example.ui.pages.MarriageRegistrationDataPage;
import org.example.ui.pages.ServiceSelectionPage;
import org.example.utils.factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("E2E тесты регистрации")
@Feature("Подача заявки пользователем")
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

    @TmsLink("155")
    @Test
    @DisplayName("Регистрация смерти - E2E")
    @Severity(SeverityLevel.CRITICAL)
    void deathRegistrationTest() {
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
    }

    @TmsLink("156")
    @Test
    @DisplayName("Регистрация рождения - E2E")
    @Severity(SeverityLevel.CRITICAL)
    void birthRegistrationTest() {
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
    }

    @TmsLink("154")
    @Test
    @DisplayName("Регистрация брака - E2E")
    @Severity(SeverityLevel.CRITICAL)
    void marriageRegistrationTest() {
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
    }
}

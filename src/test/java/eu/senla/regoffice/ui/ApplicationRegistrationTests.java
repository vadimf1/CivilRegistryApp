package eu.senla.regoffice.ui;

import eu.senla.regoffice.db.service.DbService;
import eu.senla.regoffice.constants.ApplicationType;
import io.qameta.allure.*;
import eu.senla.regoffice.models.User;
import eu.senla.regoffice.ui.pages.ApplicantDataPage;
import eu.senla.regoffice.ui.pages.ApplicationStatusPage;
import eu.senla.regoffice.ui.pages.BirthRegistrationDataPage;
import eu.senla.regoffice.ui.pages.CitizenDataPage;
import eu.senla.regoffice.ui.pages.DeathRegistrationDataPage;
import eu.senla.regoffice.ui.pages.MarriageRegistrationDataPage;
import eu.senla.regoffice.ui.pages.ServiceSelectionPage;
import eu.senla.regoffice.factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UI")
@Epic("E2E тесты регистрации")
@Feature("Подача заявки пользователем")
public class ApplicationRegistrationTests extends BaseUiTest {
    ApplicantDataPage applicantDataPage;
    ServiceSelectionPage serviceSelectionPage;
    CitizenDataPage citizenDataPage;
    ApplicationStatusPage applicationStatusPage;
    DbService dbService;

    @BeforeEach
    void setUp() {
        applicantDataPage = new ApplicantDataPage();
        serviceSelectionPage = new ServiceSelectionPage();
        citizenDataPage = new CitizenDataPage();
        applicationStatusPage = new ApplicationStatusPage();
        dbService = new DbService(connection);
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
        dbService.deleteApplicationById(dbService.getApplicationIdByCitizen(user), ApplicationType.DEATH);
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
        dbService.deleteApplicationById(dbService.getApplicationIdByCitizen(user), ApplicationType.BIRTH);
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
        dbService.deleteApplicationById(dbService.getApplicationIdByCitizen(user), ApplicationType.MARRIAGE);
    }
}

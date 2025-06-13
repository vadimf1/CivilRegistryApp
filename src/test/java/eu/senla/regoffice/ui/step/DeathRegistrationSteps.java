package eu.senla.regoffice.ui.step;

import com.codeborne.selenide.Selenide;
import eu.senla.regoffice.db.service.DbService;
import eu.senla.regoffice.constants.ApplicationType;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import eu.senla.regoffice.db.connection.ConnectionHolder;
import eu.senla.regoffice.models.User;
import eu.senla.regoffice.ui.pages.ApplicantDataPage;
import eu.senla.regoffice.ui.pages.ApplicationStatusPage;
import eu.senla.regoffice.ui.pages.CitizenDataPage;
import eu.senla.regoffice.ui.pages.DeathRegistrationDataPage;
import eu.senla.regoffice.ui.pages.ServiceSelectionPage;
import eu.senla.regoffice.factory.UserFactory;
import eu.senla.regoffice.ui.pages.MainPage;
import eu.senla.regoffice.ui.pages.PageAction;

public class DeathRegistrationSteps {

    private MainPage mainPage;
    private PageAction action;

    private final ApplicantDataPage applicantDataPage = new ApplicantDataPage();
    private final ServiceSelectionPage serviceSelectionPage = new ServiceSelectionPage();
    private final CitizenDataPage citizenDataPage = new CitizenDataPage();
    private final DeathRegistrationDataPage deathRegistrationDataPage = new DeathRegistrationDataPage();
    private final ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage();
    private final DbService dbService = new DbService(ConnectionHolder.getConnection());
    private User user;

    @Given("пользователь заходит на главную страницу и авторизуется")
    public void openMainPage() {
        mainPage = new MainPage();
        mainPage.openMainPage();
        action = new PageAction();

        user = UserFactory.createUserForDeathRegistration();
    }

    @When("входит как пользователь")
    public void enterAsUser() {
        mainPage.enterAsUser();
    }

    @And("заполняет данные заявителя")
    public void fillApplicantData() {
        applicantDataPage.fillApplicantDataForm(user);
        action.clickNext();
    }

    @And("выбирает услугу \"Регистрация смерти\"")
    public void chooseApplication() {
        serviceSelectionPage.chooseDeathRegistration();
    }

    @And("заполняет данные гражданина")
    public void fillCitizensData() {
        citizenDataPage.fillCitizenDataForm(user);
        action.clickNext();
    }

    @And("заполняет данные для регистрации смерти")
    public void fillApplicationData() {
        deathRegistrationDataPage.fillDeathRegistrationDataForm(user);
        action.clickFinish();
    }

    @Then("статус заявки должен быть успешно загружен")
    public void checkApplicationStatus() {
        applicationStatusPage.checkIsLoaded();
        dbService.deleteApplicationById(dbService.getApplicationIdByCitizen(user), ApplicationType.DEATH);
    }

    @After
    public void tearDown() {
        ConnectionHolder.closeConnection();
        Selenide.closeWebDriver();
    }
}

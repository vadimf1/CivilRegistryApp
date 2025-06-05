package org.example.ui.step;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.example.db.connection.ConnectionHolder;
import org.example.models.User;
import org.example.ui.pages.ApplicantDataPage;
import org.example.ui.pages.ApplicationStatusPage;
import org.example.ui.pages.CitizenDataPage;
import org.example.ui.pages.DeathRegistrationDataPage;
import org.example.ui.pages.ServiceSelectionPage;
import org.example.utils.factory.UserFactory;
import org.example.db.managers.ApplicationManager;
import org.example.ui.pages.MainPage;
import org.example.ui.pages.PageAction;

public class DeathRegistrationSteps {

    private MainPage mainPage;
    private PageAction action;

    private final ApplicantDataPage applicantDataPage = new ApplicantDataPage();
    private final ServiceSelectionPage serviceSelectionPage = new ServiceSelectionPage();
    private final CitizenDataPage citizenDataPage = new CitizenDataPage();
    private final DeathRegistrationDataPage deathRegistrationDataPage = new DeathRegistrationDataPage();
    private final ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage();
    private final ApplicationManager applicationManager = new ApplicationManager(ConnectionHolder.getConnection());

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
        applicationManager.deleteDeathApplicationById(applicationManager.getLastApplicationId());
    }

    @After
    public void tearDown() {
        ConnectionHolder.closeConnection();
        Selenide.closeWebDriver();
    }
}

package eu.senla.regoffice.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import eu.senla.regoffice.models.User;

import java.time.Duration;

@Slf4j
public class BirthRegistrationDataPage {
    private final SelenideElement INPUT_BIRTH_PACE = Selenide.$x("//div[label[text() = 'Место рождения']]/following-sibling::input");
    private final SelenideElement INPUT_MOTHER_INFO = Selenide.$x("//div[label[text() = 'Мать']]/following-sibling::input");
    private final SelenideElement INPUT_FATHER_INFO = Selenide.$x("//div[label[text() = 'Отец']]/following-sibling::input");
    private final SelenideElement INPUT_GRANDMOTHER_INFO = Selenide.$x("//div[label[text() = 'Бабушка']]/following-sibling::input");
    private final SelenideElement INPUT_GRANDFATHER_INFO = Selenide.$x("//div[label[text() = 'Дедушка']]/following-sibling::input");

    @Step("Проверка загрузки страницы ввода данных о рождении")
    private void isLoaded() {
        INPUT_BIRTH_PACE.shouldBe(Condition.visible, Duration.ofSeconds(10));
        log.info("Проверка загрузки страницы ввода данных о рождении");
    }

    @Step("Ввод места рождения: {birthPlace}")
    private void fillBirthPlace(String birthPlace) {
        INPUT_BIRTH_PACE.val(birthPlace);
        log.info("Ввод места рождения: {}", birthPlace);
    }

    @Step("Ввод данных матери: {motherInfo}")
    private void fillMotherInfo(String motherInfo) {
        INPUT_MOTHER_INFO.val(motherInfo);
        log.info("Ввод данных матери: {}", motherInfo);
    }

    @Step("Ввод данных отца: {fatherInfo}")
    private void fillFatherInfo(String fatherInfo) {
        INPUT_FATHER_INFO.val(fatherInfo);
        log.info("Ввод данных отца: {}", fatherInfo);
    }

    @Step("Ввод данных бабушки: {grandmotherInfo}")
    private void fillGrandmotherInfo(String grandmotherInfo) {
        INPUT_GRANDMOTHER_INFO.val(grandmotherInfo);
        log.info("Ввод данных бабушки: {}", grandmotherInfo);
    }

    @Step("Ввод данных дедушки: {grandfatherInfo}")
    private void fillGrandfatherInfo(String grandfatherInfo) {
        INPUT_GRANDFATHER_INFO.val(grandfatherInfo);
        log.info("Ввод данных дедушки: {}", grandfatherInfo);
    }

    @Step("Заполнение формы регистрации рождения")
    public void fillBirthRegistrationDataFrom(User user) {
        isLoaded();
        fillBirthPlace(user.getBirthPlace());
        fillMotherInfo(user.getMotherInfo());
        fillFatherInfo(user.getFatherInfo());
        fillGrandmotherInfo(user.getGrandmotherInfo());
        fillGrandfatherInfo(user.getGrandfatherInfo());
        log.info("Заполнение формы регистрации рождения");
    }
}

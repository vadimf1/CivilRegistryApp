package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.example.models.User;

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
        log.info("Проверка загрузки страницы ввода данных о рождении");
        INPUT_BIRTH_PACE.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Ввод места рождения: {birthPlace}")
    private void fillBirthPlace(String birthPlace) {
        log.info("Ввод места рождения: {}", birthPlace);
        INPUT_BIRTH_PACE.val(birthPlace);
    }

    @Step("Ввод данных матери: {motherInfo}")
    private void fillMotherInfo(String motherInfo) {
        log.info("Ввод данных матери: {}", motherInfo);
        INPUT_MOTHER_INFO.val(motherInfo);
    }

    @Step("Ввод данных отца: {fatherInfo}")
    private void fillFatherInfo(String fatherInfo) {
        log.info("Ввод данных отца: {}", fatherInfo);
        INPUT_FATHER_INFO.val(fatherInfo);
    }

    @Step("Ввод данных бабушки: {grandmotherInfo}")
    private void fillGrandmotherInfo(String grandmotherInfo) {
        log.info("Ввод данных бабушки: {}", grandmotherInfo);
        INPUT_GRANDMOTHER_INFO.val(grandmotherInfo);
    }

    @Step("Ввод данных дедушки: {grandfatherInfo}")
    private void fillGrandfatherInfo(String grandfatherInfo) {
        log.info("Ввод данных дедушки: {}", grandfatherInfo);
        INPUT_GRANDFATHER_INFO.val(grandfatherInfo);
    }

    @Step("Заполнение формы регистрации рождения")
    public void fillBirthRegistrationDataFrom(User user) {
        log.info("Заполнение формы регистрации рождения");
        isLoaded();
        fillBirthPlace(user.getBirthPlace());
        fillMotherInfo(user.getMotherInfo());
        fillFatherInfo(user.getFatherInfo());
        fillGrandmotherInfo(user.getGrandmotherInfo());
        fillGrandfatherInfo(user.getGrandfatherInfo());
    }
}

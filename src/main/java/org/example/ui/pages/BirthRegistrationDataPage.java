package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.models.User;

import java.time.Duration;

public class BirthRegistrationDataPage {
    private final SelenideElement INPUT_BIRTH_PACE = Selenide.$x("//div[label[text() = 'Место рождения']]/following-sibling::input");
    private final SelenideElement INPUT_MOTHER_INFO = Selenide.$x("//div[label[text() = 'Мать']]/following-sibling::input");
    private final SelenideElement INPUT_FATHER_INFO = Selenide.$x("//div[label[text() = 'Отец']]/following-sibling::input");
    private final SelenideElement INPUT_GRANDMOTHER_INFO = Selenide.$x("//div[label[text() = 'Бабушка']]/following-sibling::input");
    private final SelenideElement INPUT_GRANDFATHER_INFO = Selenide.$x("//div[label[text() = 'Дедушка']]/following-sibling::input");

    private void isLoaded() {
        INPUT_BIRTH_PACE.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    private void fillBirthPlace(String birthPlace) {
        INPUT_BIRTH_PACE.val(birthPlace);
    }

    private void fillMotherInfo(String motherInfo) {
        INPUT_MOTHER_INFO.val(motherInfo);
    }

    private void fillFatherInfo(String fatherInfo) {
        INPUT_FATHER_INFO.val(fatherInfo);
    }

    private void fillGrandmotherInfo(String grandmotherInfo) {
        INPUT_GRANDMOTHER_INFO.val(grandmotherInfo);
    }

    private void fillGrandfatherInfo(String grandfatherInfo) {
        INPUT_GRANDFATHER_INFO.val(grandfatherInfo);
    }

    public void fillBirthRegistrationDataFrom(User user) {
        isLoaded();
        fillBirthPlace(user.getBirthPlace());
        fillMotherInfo(user.getMotherInfo());
        fillFatherInfo(user.getFatherInfo());
        fillGrandmotherInfo(user.getGrandmotherInfo());
        fillGrandfatherInfo(user.getGrandfatherInfo());
    }

}

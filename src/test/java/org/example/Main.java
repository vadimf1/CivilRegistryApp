package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class Main {
    public static String applicationId;

    @Test
    @Order(1)
    public void test() {
        open("https://regoffice.senla.eu/");

        $x("//button[text()='Войти как пользователь']").shouldBe(visible, Duration.ofSeconds(10)).click();

        $x("//div[label[text() = 'Фамилия']]/following-sibling::input[1]").setValue("last-name");
        $x("//div[label[text() = 'Имя']]/following-sibling::input[1]").setValue("first-name");
        $x("//div[label[text() = 'Отчество']]/following-sibling::input[1]").setValue("middle-name");
        $x("//div[label[text() = 'Телефон']]/following-sibling::input[1]").setValue("298888230");
        $x("//div[label[text() = 'Номер паспорта']]/following-sibling::input[1]").setValue("123456AA");
        $x("//div[label[text() = 'Адрес прописки']]/following-sibling::input[1]").setValue("г. Гродно, ул. Мира 5, 20");
        $x("//button[text() = 'Далее']").click();

        $x("//button[text()='Регистрация смерти']").click();

        $x("//div[label[text() = 'Фамилия']]/following-sibling::input[1]").setValue("last-name");
        $x("//div[label[text() = 'Имя']]/following-sibling::input[1]").setValue("first-name");
        $x("//div[label[text() = 'Отчество']]/following-sibling::input[1]").setValue("middle-name");
        $x("//div[label[text() = 'Дата рождения']]/following-sibling::input[1]").setValue("12.05.2010");
        $x("//div[label[text() = 'Номер паспорта']]/following-sibling::input[1]").setValue("123456AA");
        $x("//div[label[text() = 'Пол']]/following-sibling::input[1]").setValue("male");
        $x("//div[label[text() = 'Адрес прописки']]/following-sibling::input[1]").setValue("г. Гродно, ул. Мира 5, 20");
        $x("//button[text() = 'Далее']").click();

        $x("//div[label[text() = 'Дата смерти']]/following-sibling::input[1]").setValue("12.05.2010");
        $x("//div[label[text() = 'Место смерти']]/following-sibling::input[1]").setValue("г. Гродно");
        $x("//button[text() = 'Завершить']").click();

        SelenideElement info = $x("//span[contains(text(), 'Ваша заявка')]");

        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(10));

        String requestNumber = wait.until(driver -> {
            String text = info.getText();
            Matcher matcher = Pattern.compile("заявка №\\s*(\\d+)").matcher(text);
            return matcher.find() ? matcher.group(1) : null;
        });

        applicationId = requestNumber;
    }

    @Test
    @Order(2)
    public void test2() {
        open("https://regoffice.senla.eu/");

        $x("//button[text()='Войти как администратор']").shouldBe(visible, Duration.ofSeconds(10)).click();

        $x("//div[label[text() = 'Фамилия']]/following-sibling::input[1]").setValue("last-name");
        $x("//div[label[text() = 'Имя']]/following-sibling::input[1]").setValue("first-name");
        $x("//div[label[text() = 'Отчество']]/following-sibling::input[1]").setValue("middle-name");
        $x("//div[label[text() = 'Телефон']]/following-sibling::input[1]").setValue("298888230");
        $x("//div[label[text() = 'Номер паспорта']]/following-sibling::input[1]").setValue("123456AA");
        $x("//div[label[text() = 'Дата рождения']]/following-sibling::input[1]").setValue("12.05.2010");
        $x("//button[text() = 'Далее']").click();

        $x("//table").shouldBe(visible, Duration.ofSeconds(10));

        boolean isPresent = $$x("//table//tr").stream()
                .anyMatch(row -> {
                    ElementsCollection cells = row.$$x("td");
                    return !cells.isEmpty() && cells.get(0).getText().equals(applicationId);
                });

        Assertions.assertTrue(isPresent);
    }
}

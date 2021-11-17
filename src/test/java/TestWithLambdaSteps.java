import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class TestWithLambdaSteps {
    private static final String TITLE = "Text Box";

    Faker faker = new Faker(new Locale("ru"));
    String fullName = faker.name().fullName();
    String email = faker.internet().emailAddress();
    String currentAddress = faker.address().streetAddress();
    String randomText = Integer.toString(faker.number().randomDigit());

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    @DisplayName("Проверка формы. LambdaSteps")
    public void testWithLambdaSteps() {
        step("Открыть страницу ", () -> open("https://demoqa.com/text-box"));
        step("Проверить заголовок страницы" + TITLE, () -> {
            $(".main-header").shouldHave(Condition.text(TITLE));
        });
        step("Заполнение формы рандомными данными", () -> {
            $(By.id("userName")).setValue(fullName);
            $(By.id("userEmail")).setValue(email);
            $(By.id("currentAddress")).setValue(currentAddress);
            $(By.id("permanentAddress")).setValue(randomText);
        });
        step("Сохранение формы", () -> $(By.id("submit")).click());
        step("Проверка данных", () -> {
            $x("//p[@id='name']").shouldHave(Condition.text("Name:")).shouldHave(Condition.text(fullName));
            $x("//p[@id='email']").shouldHave(Condition.text("Email:")).shouldHave(Condition.text(email));
            $x("//p[@id='currentAddress']").shouldHave(Condition.text("Current Address :")).shouldHave(Condition.text(currentAddress));
            $x("//p[@id='permanentAddress']").shouldHave(Condition.text("Permananet Address :")).shouldHave(Condition.text(randomText));
        });
    }
}

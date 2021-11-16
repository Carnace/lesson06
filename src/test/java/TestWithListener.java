import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class TestWithListener {

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
    public void testWithListener(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://demoqa.com/text-box");
        $(".main-header").shouldHave(Condition.text(TITLE));

        $(By.id("userName")).setValue(fullName);
        $(By.id("userEmail")).setValue(email);
        $(By.id("currentAddress")).setValue(currentAddress);
        $(By.id("permanentAddress")).setValue(randomText);

        $(By.id("submit")).click();

        $x("//p[@id='name']").shouldHave(Condition.text("Name:")).shouldHave(Condition.text(fullName));
        $x("//p[@id='email']").shouldHave(Condition.text("Email:")).shouldHave(Condition.text(email));
        $x("//p[@id='currentAddress']").shouldHave(Condition.text("Current Address :")).shouldHave(Condition.text(currentAddress));
        $x("//p[@id='permanentAddress']").shouldHave(Condition.text("Permananet Address :")).shouldHave(Condition.text(randomText));

    }
}

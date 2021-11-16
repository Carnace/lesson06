import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import steps.textBoxSteps;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class TestWithSteps {

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
    public void testWithStep(){
        textBoxSteps.openPage();
        textBoxSteps.fillForm(fullName,email,currentAddress,randomText);
        textBoxSteps.clickBySubmit();
        textBoxSteps.checkFormWithData(fullName,email,currentAddress,randomText);
    }
}

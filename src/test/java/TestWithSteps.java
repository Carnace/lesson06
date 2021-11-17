import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.textBoxSteps;

import java.util.Locale;

public class TestWithSteps {

    Faker faker = new Faker(new Locale("ru"));
    String fullName = faker.name().fullName();
    String email = faker.internet().emailAddress();
    String currentAddress = faker.address().streetAddress();
    String randomText = Integer.toString(faker.number().randomDigit());

    textBoxSteps onTextBoxSteps = new textBoxSteps();


    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @DisplayName("Проверка формы. Steps")
    @Test
    public void testWithSteps(){
        onTextBoxSteps.openPage();
        onTextBoxSteps.fillForm(fullName,email,currentAddress,randomText);
        onTextBoxSteps.clickBySubmit();
        onTextBoxSteps.checkFormWithData(fullName,email,currentAddress,randomText);
    }
}

package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class textBoxSteps {

    private static final String TITLE = "Text Box";

    @Step("Открытие страницы")
    public void openPage(){
        open("https://demoqa.com/text-box");
        $(".main-header").shouldHave(Condition.text(TITLE));
    }

    @Step("Заполнение формы")
    public void fillForm(String fullName, String email, String currentAddress, String text) {
        $(By.id("userName")).setValue(fullName);
        $(By.id("userEmail")).setValue(email);
        $(By.id("currentAddress")).setValue(currentAddress);
        $(By.id("permanentAddress")).setValue(text);
    }

    @Step("Нажатие на кнопку 'Submit'")
    public void clickBySubmit() {
        $(By.id("submit")).click();
    }

    @Step("Проверка выходной формы")
    public void checkFormWithData(String fullName, String email, String currentAddress, String text) {
        $x("//p[@id='name']").shouldHave(Condition.text("Name:")).shouldHave(Condition.text(fullName));
        $x("//p[@id='email']").shouldHave(Condition.text("Email:")).shouldHave(Condition.text(email));
        $x("//p[@id='currentAddress']").shouldHave(Condition.text("Current Address :")).shouldHave(Condition.text(currentAddress));
        $x("//p[@id='permanentAddress']").shouldHave(Condition.text("Permananet Address :")).shouldHave(Condition.text(text));
    }
}

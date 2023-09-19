import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class TheFirstAutotest {
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void registrationForm() {
        open("/automation-practice-form");

        executeJavaScript("document.getElementById('submit').click()");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Petr");
        $("#lastName").setValue("Petrov");
        $("#userEmail").setValue("petrovpetr@example.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("9000000000");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").$(byText("January")).click();
        $(".react-datepicker__year-select").$(byText("1995")).click();
        $(".react-datepicker__day.react-datepicker__day--030").click();
        $("#subjectsInput").setValue("History").pressEnter();
        $(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("petrov.jpg");
        $("#currentAddress").setValue("Current Address");
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();
        $("#submit").click();
        $(".modal-dialog").shouldHave(text("Thanks for submitting the form"));
        $(".modal-content").shouldHave(
                text("Petr Petrov"),
                text("petrovpetr@example.com"),
                text("Male"),
                text("9000000000"),
                text("30 January,1995"),
                text("History"),
                text("Reading"),
                text("petrov.jpg"),
                text("Current Address"),
                text("Haryana Karnal"));
    }
}


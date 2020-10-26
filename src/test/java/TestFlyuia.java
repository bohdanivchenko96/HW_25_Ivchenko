import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.List;

public class TestFlyuia {


    @Test
    public void testFlyuia(){
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://www.flyuia.com/";
        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = true;
        open("/");
        $(By.id("SEARCH_WIDGET_FORM_BUTTONS_ROUND_TRIP")).shouldBe(visible);
        $(By.cssSelector("input[placeholder='Departure']")).setValue("Vienna");
        $(By.cssSelector("span[class='highlight']")).shouldBe(visible).click();
        $(By.cssSelector("input[placeholder='Arrival']")).setValue("Kyiv");
        $(By.cssSelector("span[class='highlight']")).shouldBe(visible).click();
        $(By.cssSelector("sw-form-control-datepicker[formcontrolname='departureDate']")).click();
        $(By.cssSelector("i[class='obe-sw-icon-navigate_next']")).shouldBe(visible).click();
        $(By.cssSelector("span[class='fx-flex-40']")).shouldHave(text("November")).shouldBe(visible);
        $$(By.cssSelector("button[class='calendar-day calendar-day-btn fx-flex-fill fx-row__center__center']")).get(18).click();

        $(By.cssSelector("sw-form-control-datepicker[formcontrolname='returnDate']")).click();
        $(By.cssSelector("span[class='fx-flex-40']")).shouldHave(text("November")).shouldBe(visible);
        $$(By.cssSelector("button[class='calendar-day calendar-day-btn fx-flex-fill fx-row__center__center']")).get(2).click();
        System.out.println("data is selected ");
        $(By.cssSelector("span[class='obe-sw-icon-passenger']")).click();
        $(By.cssSelector("button[class='set-val-btn fx-row__center__center fx-flex-15']")).shouldBe(visible);
        $$(By.cssSelector("button[class='set-val-btn fx-row__center__center fx-flex-15']")).get(1).click();
        System.out.println("passanger is added");
        $(By.id("SEARCH_WIDGET_FORM_BUTTONS_SEARCH_FLIGHTS")).click();
        $(By.cssSelector("div[class='product__title']")).shouldBe(visible);
        $(By.className("icon-close")).shouldBe(visible).click();






    }
}

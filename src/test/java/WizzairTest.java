import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;


public class WizzairTest {

    @Test
    public static void DataTestWazzair() {
        Configuration.browser = "chrome";
        Configuration.timeout = 45000;
        Configuration.baseUrl = "https://wizzair.com/#/";
        open("/");

        $(By.id("search-departure-station")).shouldBe(visible).setValue("Vienna");
        $(By.tagName("mark")).shouldHave(text("Vienna")).click();
        $(By.id("search-arrival-station")).setValue("Kyiv");
        $(By.tagName("mark")).shouldHave(text("Kyiv")).click();
        $(By.id("search-departure-date")).click();

        $(By.className("pika-lendar")).shouldBe(visible);
        $(By.cssSelector("button[data-pika-month='10'][data-pika-day='19']")).click();
        $(By.className("pika-lendar")).shouldBe(visible);
        $(By.cssSelector("button[data-pika-month='10'][data-pika-day='22']")).click();
        $(By.className("pika-lendar")).shouldBe(visible);
        $(By.cssSelector("button[data-test='calendar-shrinkable-ok-button']")).shouldBe(visible).click();
        $(By.id("search-passenger")).click();
        $$(By.cssSelector("button[class='stepper__button stepper__button--add']")).first().click();
        $(By.cssSelector("button[data-test='flight-search-hide-panel']")).shouldBe(visible).click();
        $(By.cssSelector("button[data-test='flight-search-submit']")).click();
        switchTo().window(0).close();
        switchTo().window(0);
        switchTo().defaultContent();
        while ($(By.className("flight-select__flight")).is(not(visible))) {
            Wait();
            if ($(By.className("modal__inner")).is(visible)) {
                $(By.className("modal__close")).click();
            }
        }
        Assert.assertEquals($$(By.className("flight-select__flight__line")).size(), 2);
        System.out.println("Yes there are 2 block with available tickets!");
        List<String> flightDate = $$(By.cssSelector("time[class='flight-select__flight-info__day'")).texts();
        Assert.assertEquals(flightDate.get(0), "Thu, 19 Nov 2020");
        Assert.assertEquals(flightDate.get(1), "Sun, 22 Nov 2020");
        System.out.println("The dates in each tickets are correct!");



    }

}

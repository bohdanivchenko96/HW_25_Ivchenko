import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class TestRyanair {

    @Test
    public void testRyanair(){
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://www.ryanair.com";
        Configuration.startMaximized = true;
        open("/");
        $(By.id("input-button__departure")).shouldBe(visible).clear();
        $(By.id("input-button__departure")).shouldBe(visible).setValue("Vienna");
        $(By.cssSelector("span[data-ref='airport-item__name']")).click();
        $(By.id("input-button__destination")).shouldBe(visible).setValue("Kyiv");
        $(By.cssSelector("span[data-ref='airport-item__name']")).click();
        $(By.cssSelector("div[data-id='2020-11-19']")).shouldBe(visible).click();
        $(By.cssSelector("div[data-id='2020-11-21']")).shouldBe(visible).click();
        $$(By.cssSelector("div[class='counter__button-wrapper--enabled']")).first().click();
        $(By.cssSelector("button[color='anchor-blue']")).scrollIntoView(true).click();
        $(By.cssSelector("button[data-ref='flight-search-widget__cta']")).click();
        $(By.cssSelector("div[class='header']")).shouldBe(visible);
        Assert.assertEquals($$(By.cssSelector("div[class='header']")).size(), 2);
        System.out.println("Yes there are 2 block with available tickets!");
        List<String> flightDate = $$(By.cssSelector("button[data-selected='true']")).texts();
        Assert.assertTrue(flightDate.get(0).contains("19"));
        Assert.assertTrue(flightDate.get(1).contains("21"));
        System.out.println("The dates in each tickets are correct!");


    }
}

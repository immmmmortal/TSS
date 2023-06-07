package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class testChangeLanguage{

    WebDriver driver;

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        driver = new ChromeDriver();
        driver.get("http://localhost:7000/login");
    }

    @When("I click the language switcher button")
    public void i_click_the_language_switcher_button() {

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5).getSeconds())
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/a[1]")));
        driver.findElement(By.xpath("/html/body/header/div/a[1]")).click();
    }

    @Then("the language on the page should change")
    public void the_language_on_the_page_should_change() {

        WebElement displayedElement = driver.findElement(By.xpath("/html/body/main/div[1]/div/div[2]/form/button"));
        assertEquals("Sign in", displayedElement.getText());
        driver.quit();
    }
}

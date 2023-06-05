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
import static org.junit.Assert.assertTrue;

public class testShowPasswordSteps {
    WebDriver driver;
    @Given("User is on the login page")
    public void i_am_on_the_login_page() {

        driver = new ChromeDriver();
        driver.get("http://localhost:7000/login");
    }

    @When("I click the show password button")
    public void i_click_the_show_password_button() {

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id("pass-show")));
        driver.findElement(By.id("pass-show")).click();
    }

    @Then("I should be able to see the password")
    public void i_should_be_able_to_see_the_password() {

        WebElement passwordInput = driver.findElement(By.name("password"));
        assertEquals("text", passwordInput.getAttribute("type"));
    }

    @Then("I should be able to hide the password")
    public void i_should_be_able_to_hide_the_password() {

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id("pass-hide")));
        driver.findElement(By.id("pass-hide")).click();
        WebElement passwordInput = driver.findElement(By.name("password"));
        assertEquals("password", passwordInput.getAttribute("type"));
        driver.quit();
    }
}


package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.*;

public class ResetUsernameStepDefinitions {
    WebDriver driver = null;
    @Given("the user is logged in account")
    public void i_am_logged_in_to_my_account() {
        driver = new ChromeDriver();
        new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.navigate().to("http://localhost:7000/login");

        driver.findElement(By.name("login")).sendKeys("wanin69399@ratedane.com");
        driver.findElement(By.name("password")).sendKeys("76Xday6955-");
        driver.findElement(By.xpath("/html/body/main/div[1]/div/div[2]/form/button")).click();
    }

    @When("the user resets their username")
    public void theUserResetsTheirUsernameTo() {
        driver.get("http://localhost:7000/profile");
        String new_username = "exampleUsername1";
        driver.findElement(By.id("db-username")).clear();
        driver.findElement(By.id("db-username")).sendKeys(new_username);
        driver.findElement(By.xpath("/html/body/div/div[1]/main/div[4]/div/form[1]/div[2]/button")).click();
    }

    @Then("the database username should be updated")
    public void theDatabaseUsernameShouldBeUpdatedTo() {
        WebElement username_element = driver.findElement(By.xpath("//*[@id=\"db-username\"]"));
        String current_username = username_element.getAttribute("value");
        assertEquals("exampleUsername1", current_username);

        // im not checking if db_username is updated in db since we retrieve data straight from user model
    }

    @And("the user should be redirected to the basic page")
    public void theUserShouldBeRedirectedToTheBasicPage() {
        String expectedPageUrl = "http://localhost:7000/profile";
        assertTrue(driver.getCurrentUrl().equals(expectedPageUrl));
    }

    @And("the message should be displayed")
    public void theMessageShouldBeDisplayed() {

        WebElement success_message_element = driver.findElement(By.xpath("/html/body/div/div[1]/main/div[1]"));
        String expected_message = success_message_element.getText();
        String message = "DB username was changed";

        assertEquals(message, expected_message);
    }
}

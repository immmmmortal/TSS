package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class testLoginSteps {
    WebDriver driver = null;
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver = new ChromeDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to("http://localhost:7000/login");
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        driver.findElement(By.name("login")).sendKeys("kuza617@gmail.com");
        driver.findElement(By.name("password")).sendKeys(".kYM8jRj8cJCpuD");
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
       driver.findElement(By.xpath("/html/body/main/div[1]/div/div[2]/form/button")).click();
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        driver.findElement(By.linkText("Logout")).isDisplayed();
        driver.close();
        driver.quit();
    }
}

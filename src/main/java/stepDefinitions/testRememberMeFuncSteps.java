package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class testRememberMeFuncSteps {
    WebDriver driver = null;
    @Given("User on the login page")
    public void user_on_the_login_page() {
        driver = new ChromeDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        driver.navigate().to("http://localhost:7000/login");
    }
    @When("I enter valid credentials with remember me checked and click login")
    public void i_enter_valid_credentials_with_remember_me_checked() {
        driver.findElement(By.name("login")).sendKeys("kuza617@gmail.com");
        driver.findElement(By.name("password")).sendKeys(".kYM8jRj8cJCpuD");
        driver.findElement(By.xpath("/html/body/main/div[1]/div/div[2]/form/div[3]/label/input")).click();
        driver.findElement(By.xpath("/html/body/main/div[1]/div/div[2]/form/button")).click();
    }
    @Then("User should be logged in")
    public void user_should_be_logged_in() {

    }
    }


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
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class testCreateDBViaProfileSteps {

    WebDriver driver;

    @Given("I am logged in to my account")
    public void i_am_logged_in_to_my_account() {

        driver = new ChromeDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to("http://localhost:7000/login");



        driver.findElement(By.name("login")).sendKeys("kuza617@gmail.com");
        driver.findElement(By.name("password")).sendKeys(".kYM8jRj8cJCpuD");
        driver.findElement(By.xpath("/html/body/main/div[1]/div/div[2]/form/button")).click();
    }

    @When("I create a new database on my profile page")
    public void i_create_a_new_database_with_name() {

        WebElement createDatabaseButton = driver.findElement(By.xpath("/html/body/div/div/main/div[3]/form/button"));
        createDatabaseButton.click();
    }

    @Then("database should be listed in my account info")
    public void should_be_listed_in_my_account_info() {

        driver.get("http://localhost:7000");


        boolean databaseList = driver.findElement(By.xpath("/html/body/div/div/main/div[3]/div/div[2]")).isDisplayed();
        assertTrue(databaseList);

        driver.quit();
    }
}
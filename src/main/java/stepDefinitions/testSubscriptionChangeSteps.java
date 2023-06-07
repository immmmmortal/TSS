package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class testSubscriptionChangeSteps {

    WebDriver driver;

    @Given("I am loged in to my account")
    public void i_am_loged_in_to_my_account() {
        driver = new ChromeDriver();
        driver.get("http://localhost:7000/login");
        driver.findElement(By.name("login")).sendKeys("kuza617@gmail.com");
        driver.findElement(By.name("password")).sendKeys(".kYM8jRj8cJCpuD");
        driver.findElement(By.xpath("/html/body/main/div[1]/div/div[2]/form/button")).click();
    }

    @And("I am on profile page")
    public void i_am_on_profile_page() {
        driver.get("http://localhost:7000/profile");
    }

    @And("I select a different subscription plan")
    public void i_select_a_different_subscription_plan() {
        driver.findElement(By.xpath("//*[contains(text(), 'Upgrade')]")).click();
    }


    @Then("I should see a notification that my plan has been changed")
    public void i_should_see_a_notification_that_my_plan_has_been_changed() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]")));
        WebElement notification = driver.findElement(By.xpath("/html/body/div/div/main/div[1]"));
        assertEquals(notification.getText(), "Role has been changed");
    }

}


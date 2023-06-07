package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class testHomePageSteps {
    WebDriver driver;
    @Given("I am on home page")
    public void i_am_on_home_page() throws Throwable {
        driver = new ChromeDriver();
        driver.get("http://localhost:7000/");
    }

    @Then("I should see the website logo")
    public void i_should_see_website_logo() throws Throwable {
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/a")));
    }

    @Then("I should see the navigation menu with links")
    public void i_should_see_navigation_menu() throws Throwable {

        WebElement navMenu = driver.findElement(By.xpath("/html/body/header"));
        Assert.assertTrue(navMenu.isDisplayed());
        Assert.assertTrue(navMenu.getText().contains("Sign In"));
        Assert.assertTrue(navMenu.getText().contains("Sign Up"));
    }

    @Then("^I should see a footer section")
    public void i_should_see_footer_links() throws Throwable {

        WebElement footer = driver.findElement(By.xpath("//*[@id=\"hanging-icons\"]/div"));
        Assert.assertTrue(footer.isDisplayed());
        Assert.assertTrue(footer.getText().contains("Crafted for QA"));
        Assert.assertTrue(footer.getText().contains("Free"));
        Assert.assertTrue(footer.getText().contains("API"));
    }
}

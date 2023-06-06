package stepDefinitions;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.And;

import java.time.Duration;


public class testLogoutButtonDisplay {
    WebDriver driver;

    @Given("user is logged into his account")
    public void user_is_logged_in_to__account() {
        driver = new ChromeDriver();
        new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.navigate().to("http://localhost:7000/login");

        driver.findElement(By.name("login")).sendKeys("wanin69399@ratedane.com");
        driver.findElement(By.name("password")).sendKeys("76Xday6955-");
        driver.findElement(By.xpath("/html/body/main/div[1]/div/div[2]/form/button")).click();
    }

    @And("logout button has its padding right")
    public void logout_button_padding_inspection() {
        driver.get("http://localhost:7000/profile");
        String logout_padding = driver.findElement(By.xpath("/html/body/header/div/span")).getCssValue("padding-right");
        assertEquals("10px", logout_padding);
    }
}

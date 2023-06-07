package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;


public class TestSignUpSteps {
    WebDriver driver = null;
    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        driver = new ChromeDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        driver.navigate().to("http://localhost:7000/registration");
    }
    @When("I enter credentials for registration")
    public void i_enter_credentials_for_registration() {
        driver.findElement(By.name("login")).sendKeys("hefep93409@mirtox.com");
        driver.findElement(By.name("password")).sendKeys("*@*Pad3=(e8qZ$vQ");
        driver.findElement(By.name("conformation")).sendKeys("*@*Pad3=(e8qZ$vQ");
    }
    @When("I click Sign up")
    public void i_click_sign_up() {
        driver.findElement(By.xpath("/html/body/main/div/div/div[2]/form/button")).click();
    }
    @Then("I am logged in")
    public void i_should_be_logged_in() throws SQLException {
        driver.findElement(By.linkText("Logout")).isDisplayed();

        String login = "hefep93409@mirtox.com";
        String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=admin";
        Connection conn = DriverManager.getConnection(url);

        try {
            PreparedStatement pstmnt = conn.prepareStatement("DELETE FROM public.users WHERE username = ?");
            pstmnt.setString(1, login);
            pstmnt.executeUpdate();
        } catch (SQLException e) {System.out.println("Error: " + e.getMessage()); }
        driver.close();
        driver.quit();
    }
}

package com.example.todoapp.ui_tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTests {
    private static final String URL = "http://localhost:8081";
    private static WebDriver driver;

    @Before
    public void setUp() {
        // Set up Chrome driver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testPageTitle() {
        driver.get(URL);
        final String expectedTitle = "Todo App";
        final String actualTitle = driver.getTitle();

        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testLoginWithCorrectCredentials() {
        driver.get(URL);

        WebElement loginButton = driver.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)"));
        loginButton.click();
        WebElement loginTitle = driver.findElement(By.cssSelector(".mb-4"));

        assertEquals("Login", loginTitle.getText());

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));

        username.sendKeys("hsivov");
        password.sendKeys("123456");

        WebElement submitButton = driver.findElement(By.cssSelector(".btn"));
        submitButton.click();

        String currentUrl = driver.getCurrentUrl();

        assertEquals(URL + "/home", currentUrl);
    }

    @Test
    public void testLoginWithEmptyCredentials() {
        driver.get(URL);

        WebElement loginButton = driver.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)"));
        loginButton.click();
        WebElement loginTitle = driver.findElement(By.cssSelector(".mb-4"));

        assertEquals("Login", loginTitle.getText());

        WebElement submitButton = driver.findElement(By.cssSelector(".btn"));
        submitButton.click();

        WebElement warningUsername = driver.findElement(By.cssSelector("div.text-danger:nth-child(1)"));
        WebElement warningPassword = driver.findElement(By.cssSelector("div.text-danger:nth-child(2)"));

        assertEquals("Username must be between 3 and 20 characters!", warningUsername.getText());
        assertEquals("Password must be between 3 and 20 characters!", warningPassword.getText());
    }

    @Test
    public void testLoginWithWrongCredentials() {
        driver.get(URL);

        WebElement loginButton = driver.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)"));
        loginButton.click();
        WebElement loginTitle = driver.findElement(By.cssSelector(".mb-4"));

        assertEquals("Login", loginTitle.getText());

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));

        username.sendKeys("test");
        password.sendKeys("test");

        WebElement submitButton = driver.findElement(By.cssSelector(".btn"));
        submitButton.click();

        WebElement message = driver.findElement(By.cssSelector(".text-danger"));
        String currentUrl = driver.getCurrentUrl();

        assertEquals("Incorrect username or password!", message.getText());
        assertEquals(URL + "/login", currentUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

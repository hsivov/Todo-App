package com.example.todoapp.ui_tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RegisterPageTest {
    private static final String URL = "https://terraformhristo.azurewebsites.net";
    private static WebDriver driver;

    @Before
    public void setUp() {
        // Set up Chrome driver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testRegisterWithCorrectCredentials() {
        driver.get(URL);

        WebElement registerButton = driver.findElement(By.cssSelector("li.nav-item:nth-child(2) > a:nth-child(1)"));
        registerButton.click();

        String expectedUrl = URL + "/register";

        assertEquals(expectedUrl, driver.getCurrentUrl().split(";")[0]);

        WebElement username = driver.findElement(By.name("username"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement confirmPassword = driver.findElement(By.name("confirmPassword"));

        username.sendKeys("test");
        email.sendKeys("test@email.com");
        password.sendKeys("1234");
        confirmPassword.sendKeys("1234");

        WebElement submit = driver.findElement(By.cssSelector(".btn"));
        submit.click();

        assertEquals(URL + "/login", driver.getCurrentUrl());
    }
}

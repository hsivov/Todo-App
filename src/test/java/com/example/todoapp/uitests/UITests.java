package com.example.todoapp.uitests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITests {
    private static final String URL = "http://localhost:8081";
    private static WebDriver driver;

    @Before
    public void setUp() {
        // Set up Chrome driver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testPageTitle() {
        driver.get(URL);
        final String expectedTitle = "Todo App";
        final String actualTitle = driver.getTitle();

        assertEquals(expectedTitle, actualTitle);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

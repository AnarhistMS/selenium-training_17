package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        driver.get(" http://localhost/litecart/admin/.");
        driver.findElement(By.xpath("//input[@type=\"text\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@value=\"Login\"]")).click();
        wait.until(titleIs("My Store"));
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> ducks = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]//a[contains(@href,'edit_product')]"));
        for (int i = 0; i < ducks.size(); i++) {
            List<WebElement> updatedDucks = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]//a[contains(@href,'edit_product')]"));
            updatedDucks.get(i).click();
            wait.until(titleContains("Edit Product"));
            driver.manage().logs().get("browser").getAll().forEach(message -> {
                Assert.assertTrue(message.equals(""));
            });
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            wait.until(titleIs("Catalog | My Store"));
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver= null;
    }
}
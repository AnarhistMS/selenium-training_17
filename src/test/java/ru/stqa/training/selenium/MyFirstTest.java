package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        wait.until(titleIs("Dashboard | My Store"));
        int numberOfElements = driver.findElements(By.xpath("//ul[@id=\"box-apps-menu\"]/li")).size();
        for (int i = 1; i <= numberOfElements; i++){
            driver.findElement(By.xpath(String.format("//ul[@id=\"box-apps-menu\"]/li[%d]", i))).click();
            int numberOfInsideElements = driver.findElements(By.xpath("//ul[@class=\"docs\"]/li")).size();
            for (int j = 1; j <= numberOfInsideElements; j++){
                driver.findElement(By.xpath(String.format("//ul[@class=\"docs\"]/li[%d]", j))).click();
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver= null;
    }
}
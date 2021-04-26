package edu.ait.runners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Login {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ELKPEAT\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Maximize browser
        driver.get("http://localhost:8080/books");
        // Open website
        driver.manage().window().maximize();
        driver.findElement(By.id("add_button")).click();

        driver.findElement(By.id("title")).sendKeys("Test");
        driver.findElement(By.id("author")).sendKeys("Test Author - Patrick Kelly");
        driver.findElement(By.id("publisher")).sendKeys("AIT");
        driver.findElement(By.id("publishedDate")).sendKeys("1605-05-18");
        driver.findElement(By.id("genre")).sendKeys("Novel");
        driver.findElement(By.id("description")).sendKeys("This is to test author");
        driver.findElement(By.id("noOfPages")).sendKeys("123");
        driver.findElement(By.id("create_button")).click();

        String message = driver.findElement(By.tagName("h1")).getText();
        assertEquals(message,"List of Available Books in the System");

//        String pageTitle = driver.getTitle();
//        if (pageTitle.equals("Library Management")) {
//            System.out.println("Success");
//        }
        driver.quit();
    }
}

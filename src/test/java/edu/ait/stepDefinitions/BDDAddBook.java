//package edu.ait.stepDefinitions;
//
//
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import edu.ait.library.dto.Book;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.time.LocalDate;
//
//import static org.junit.Assert.assertEquals;
//
//public class BDDAddBook {
//
//    private WebDriver driver;
//
//    LocalDate date;
//
//    @Before(order=1)
//    public void setup() {
//        Book book = new Book();
//        book.setId(4);
//        book.setTitle("Test");
//        book.setAuthor("Test Author - Patrick Kelly");
//        book.setPublisher("AIT");
//        book.setPublishedDate(date);
//        book.setGenre("Novel");
//        book.setDescription("This is to test author");
//        book.setNoOfPages(222);
//        book.setPicture("test.jpg");
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ELKPEAT\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        driver = new ChromeDriver();
//    }
//
//    @After
//    public void tearDown() {
//        driver.quit();
//    }
//
//    @Given("^the user is on the list books page$")
//    public void the_user_is_on_the_books_page() throws Throwable {
//        driver.get("http://localhost:8080/books");
//    }
//
//    @When("^user clicks on create button$")
//    public void user_clicks_on_add_button() throws Throwable {
//        driver.findElement(By.name("Add New Book")).click();
//    }
//
//    @When("^the user enters valid book details$")
//    public void the_user_enters_valid_book_details() throws Throwable {
//        driver.findElement(By.id("title")).sendKeys("Test");
//        driver.findElement(By.id("author")).sendKeys("Test Author - Patrick Kelly");
//        driver.findElement(By.id("publisher")).sendKeys("AIT");
//        driver.findElement(By.id("publishedDate")).sendKeys("1605-05-18");
//        driver.findElement(By.id("genre")).sendKeys("Novel");
//        driver.findElement(By.id("description")).sendKeys("This is to test author");
//        driver.findElement(By.id("noOfPages")).sendKeys("123");
//    }
//
//    @When("^user clicks on create button$")
//    public void user_clicks_on_create_button() throws Throwable {
//        driver.findElement(By.name("Create Book")).click();
//    }
//
//    @Then("^user should see be redirected to books page$")
//    public void user_redirected_to_books_page() throws Throwable {
//        String message = driver.findElement(By.tagName("h1")).getText();
//        assertEquals(message,"List of Available Books in the System");
//    }
//
//}
//

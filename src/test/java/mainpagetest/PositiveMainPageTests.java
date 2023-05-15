package mainpagetest;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;


public class PositiveMainPageTests {

    WebDriver driver;
    Faker faker = new Faker();

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        String url = "https://easy-mr.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void verifyTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Easy Market Research";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void verifyLogo() {
        boolean logo = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/a/img")).isDisplayed();
        Assert.assertTrue(logo);
    }

    @Test
    public void changeLanguage() throws InterruptedException {
        System.out.println("Main page is opened.");
        WebElement itaLanguage = driver.findElement(By.xpath("//*[@id=\"menu-1-4e96ff3\"]/li[5]/a/span[1]"));
        itaLanguage.click();
    }

    @Test
    public void registrationForum() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"form-field-first_name\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(3000);
        driver.findElement(By.id("form-field-first_name")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("form-field-last_name")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("form-field-gender")).click();
        driver.findElement(By.id("form-field-birth_date")).click();
        driver.findElement(By.id("form-field-country")).click();
        driver.findElement(By.xpath("//*[@id=\"form-field-country\"]/option[2]")).click();
        driver.findElement(By.id("form-field-race")).click();
        driver.findElement(By.xpath("//*[@id=\"form-field-race\"]/option[3]")).click();
        driver.findElement(By.id("form-field-residence_state")).click();
        driver.findElement(By.xpath("//*[@id=\"form-field-residence_state\"]/option[2]")).click();
        String emailUser = faker.internet().emailAddress();
        driver.findElement(By.id("form-field-phone")).sendKeys(faker.phoneNumber().phoneNumber());
        driver.findElement(By.id("form-field-email")).sendKeys(emailUser);
        driver.findElement(By.id("form-field-email_confirm")).sendKeys(emailUser);
    }

    @Test
    public void mobileInfo(){
        driver.findElement(By.id("form-field-mobile_info")).click();
        driver.findElement(By.xpath("//*[@id=\"form-field-mobile_info\"]/option[3]")).click();
        driver.findElement(By.id("form-field-stream_music-0")).click();
        driver.findElement(By.id("form-field-shop_phone-0")).click();
        driver.findElement(By.id("form-field-phone_old-0")).click();
        driver.findElement(By.id("form-field-stream_movie-0")).click();
        driver.findElement(By.id("form-field-use_apps-0")).click();
        driver.findElement(By.id("form-field-use_apps-1")).click();
        driver.findElement(By.id("form-field-field_1f80c49")).click();


    }
        @AfterMethod
        public void tearDown () {
            driver.quit();
        }
    }


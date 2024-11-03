import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class EssTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "******");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1) // Test the functionality of requesting new annual leave
            public void testAnnualLeaveFunctionality() {

        driver.get("******");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Login as employee
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.login-field__input input[name='email']")));
        emailInput.sendKeys("******");

        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.login-field__input input[name='password']")));
        passwordInput.sendKeys("******");
        driver.findElement(By.className("button-main__label")).click();

        // Request new annual leave
        WebElement dashboardCard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".lp-dashboard-card[type='router-link']")));
        dashboardCard.click();

        WebElement plusSign = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.my-leaves__mobile-new > div > button")));
        plusSign.click();

        //select date ranges (annual leave start / end)
        WebElement startDate = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("2024-11-24")));
        startDate.click();
        driver.findElement(By.id("2024-11-28")).click();

        // Add Notes
        WebElement notes = driver.findElement(By.id("notes"));
        notes.sendKeys("Annual vacation days");

        // Confirm request.
        driver.findElement(By.xpath("//button[@data-test='submit-approve']")).click();
        driver.findElement(By.xpath("//button[@data-test='primary-action-button']")).click();
        //Request has been submitted
    }
    @Test(priority = 2)
    public void testAnnualLeaveApproval() {
        driver.get("******");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Login as Manger
        WebElement managerEmail = driver.findElement(By.cssSelector("div.login-field__input input[name='email']"));
        managerEmail.sendKeys("******");

        WebElement managerPassword = driver.findElement(By.cssSelector("div.login-field__input input[name='password']"));
        managerPassword.sendKeys("******");
        driver.findElement(By.className("button-main__label")).click();

        // Open "Leaves" from dropdown menu
        driver.findElement(By.xpath("//div[contains(@class, 'menu-link__title') and contains(., 'Leaves')]")).click();
        driver.findElement(By.xpath("//a[text()='Leaves']")).click();

        // Open leaves approval tab
        driver.findElement(By.cssSelector("div.user-name")).click();

        //Approve request leave.
        driver.findElement(By.xpath("//button[span[text()='Approve']]")).click();
        driver.findElement(By.xpath("//button[@data-test='primary-action-button']")).click();
    }

    // close browser
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

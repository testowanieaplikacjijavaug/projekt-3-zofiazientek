import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPageObjectTests {
    private static WebDriver driver;
    private String login = "lab12@gmail.com";
    String poUrl = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }
    
        @Test
    public void loginSuccess() throws  Exception {
        LoginPageObject poLogin = new LoginPageObject(driver, poUrl);
        poLogin.login(login,"lab12");
        String expectedUrl = "http://automationpractice.com/index.php?controller=my-account";
        Assert.assertEquals(expectedUrl,driver.getCurrentUrl());
    }
    
        @Test
    public void loginFailWrongPassword(){
        LoginPageObject poLogin = new LoginPageObject(driver, poUrl);
        poLogin.login(login,"lab11");
        String expectedUrl = "http://automationpractice.com/index.php?controller=authentication";
        Assert.assertEquals(expectedUrl,driver.getCurrentUrl());
    }
    
        @Test
    public void loginFailNoSuchUser(){
        LoginPageObject poLogin = new LoginPageObject(driver, poUrl);
        poLogin.login("projekt3@gmail.com","lab12");
        String expectedUrl = "http://automationpractice.com/index.php?controller=authentication";
        Assert.assertEquals(expectedUrl,driver.getCurrentUrl());
    }
    
        @Test
    public void loginFailEmptyLogin(){
        LoginPageObject poLogin = new LoginPageObject(driver, poUrl);
        poLogin.login("","lab12");
        String errorText = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
        String expectedErrorText = "An email address required.";
        Assert.assertEquals(expectedErrorText,errorText);
    }
    
        @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }
}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPageObject {
    public WebDriver driver;
    @FindBy(name = "email")
    private WebElement email;
    @FindBy(name = "passwd")
    private WebElement password;
    @FindBy(name = "SubmitLogin")
    private WebElement submit;
    private final Wait<WebDriver> wait;


    public LoginPageObject(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void login(String u, String p) {
        email.sendKeys(u);
        password.sendKeys(p);
        submit.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

}

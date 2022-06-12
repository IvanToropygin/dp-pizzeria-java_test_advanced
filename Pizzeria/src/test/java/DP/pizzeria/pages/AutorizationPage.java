package DP.pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutorizationPage extends Page{
    private WebDriver driver;
    private WebDriverWait wait;
    public NavigationPanel navigationPanel;
    private JavascriptExecutor jsExecutor;

    @FindBy(id = "username")
    private WebElement userNameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = "[name=login]")
    private WebElement loginBtn;

    @FindBy(css = "[href *= edit]")
    public WebElement editAccount;
    @FindBy(css = "[value='Файл...']")
    public WebElement fileInput;
    @FindBy(id = "uploadPreview")
    public WebElement uploadPreview;


    public AutorizationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "my-account/");
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        this.navigationPanel = new NavigationPanel(driver, wait);
        jsExecutor = (JavascriptExecutor)driver;
    }

    public void autorize(String login, String password){
        userNameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public void uploadFile(String filePath){
        jsExecutor.executeScript("document.querySelectorAll(\"[value='Файл...']\")[0].setAttribute(\"class\", \"bla\");");
        fileInput.sendKeys(filePath);
//        wait.until(driver -> uploadPreview.isDisplayed());
    }
}

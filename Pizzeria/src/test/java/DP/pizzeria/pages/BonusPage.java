package DP.pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BonusPage extends Page{
    private WebDriver driver;
    private WebDriverWait wait;
    public NavigationPanel navigationPanel;
    private JavascriptExecutor jsExecutor;

    @FindBy(id = "bonus_username")
    public WebElement userNameInput;
    @FindBy(id = "bonus_phone")
    public WebElement phoneInput;
    @FindBy(className = "woocommerce-Button")
    public WebElement getCardBtn;


    public BonusPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "bonus/");
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        this.navigationPanel = new NavigationPanel(driver, wait);
        jsExecutor = (JavascriptExecutor)driver;
    }
}

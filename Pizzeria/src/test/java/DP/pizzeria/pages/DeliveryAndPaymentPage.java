package DP.pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeliveryAndPaymentPage extends Page{
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
    @FindBy(tagName = "iframe")
    public WebElement iframe;
    @FindBy(css = "body li:nth-child(2)")
    public WebElement secondParagraph;


    public DeliveryAndPaymentPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "delivery/");
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        this.navigationPanel = new NavigationPanel(driver, wait);
        jsExecutor = (JavascriptExecutor)driver;
    }
    public void switchToFrame(){
        driver.switchTo().frame(iframe);
    }

}

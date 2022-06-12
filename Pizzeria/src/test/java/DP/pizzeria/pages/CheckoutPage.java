package DP.pizzeria.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends Page{
    private WebDriver driver;
    private WebDriverWait wait;
    public NavigationPanel navigationPanel;

    @FindBy(id = "order_date")
    public WebElement order_date;

    @FindBy(id = "billing_first_name")
    public WebElement firstNameInput;
    @FindBy(id = "billing_last_name")
    public WebElement lastNameInput;
    @FindBy(id = "billing_address_1")
    public WebElement addressInput;
    @FindBy(id = "billing_city")
    public WebElement cityInput;
    @FindBy(id = "billing_state")
    public WebElement stateInput;
    @FindBy(id = "billing_postcode")
    public WebElement postcodeInput;
    @FindBy(id = "billing_phone")
    public WebElement phoneInput;
    @FindBy(css = ".wc_payment_methods li:nth-of-type(2)")
    public WebElement payAfterDelivery;
    @FindBy(css = ".woocommerce-terms-and-conditions-checkbox-text")
    public WebElement termsAndConditionsCheckbox;
    @FindBy(id = "place_order")
    public WebElement placeOrderBtn;

    @FindBy(className = "woocommerce-thankyou-order-received")
    public WebElement successMessage;



    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "checkout/");
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        this.navigationPanel = new NavigationPanel(driver, wait);
    }
}

package DP.pizzeria.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends Page{
    private WebDriver driver;
    private WebDriverWait wait;
    public NavigationPanel navigationPanel;

    @FindBy(css = ".quantity .input-text")
    public WebElement quantity;
    @FindBy(css = ".order-total .woocommerce-Price-amount bdi")
    public WebElement orderTotal;
    @FindBy(css = "[name=update_cart]")
    private WebElement updateBtn;
    @FindBy(className = "woocommerce-message")
    private WebElement cartUpdateMessage;
    @FindBy(id = "coupon_code")
    public WebElement couponInput;
    @FindBy(css = "[name=apply_coupon]")
    public WebElement applyCouponBtn;
    @FindBy(className = "cart-discount")
    private WebElement cartDiscount;
    @FindBy(className = "checkout-button")
    public WebElement checkoutBtn;

    private By loader = By.className(".processing");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "cart/");
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        this.navigationPanel = new NavigationPanel(driver, wait);
    }

    public void updateCart(){
        updateBtn.click();
        wait.until(driver -> cartUpdateMessage
                    .isDisplayed());
    }
    public void enterCouponAndApplyCouponBtnClick(String coupon){
        couponInput.sendKeys(coupon);
        new Actions(driver)
                .moveToElement(applyCouponBtn)
                .perform();
        applyCouponBtn.click();
        wait.until(driver -> cartDiscount
                .getText().contains(coupon));
    }
}

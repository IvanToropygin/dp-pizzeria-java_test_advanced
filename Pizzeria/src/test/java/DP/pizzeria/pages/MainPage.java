package DP.pizzeria.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Page {
    private WebDriver driver;
    private WebDriverWait wait;

    public NavigationPanel navigationPanel;

    @FindBy(tagName = "html")
    private WebElement page;
    @FindBy(css = ".ap-cat-list .attachment-shop_catalog")
    private WebElement firstProductInDrinks;
    @FindBy(css = ".ap-cat-list .add_to_cart_button")
    private WebElement firstProductInDrinksInCartBtn;
    @FindBy(css = "#product2 .item-img")
    public WebElement firstDessert;

    @FindBy(css = ".woocommerce-breadcrumb span")
    public WebElement productName;

    @FindBy(css = "banner-text p:nth-child(4)")
    private WebElement contacts;
    @FindBy(css = ".banner-text p:nth-child(3) > a")
    public WebElement facebookLink;
    @FindBy(css = ".banner-text p:nth-child(4) > a")
    public WebElement vkLink;
    @FindBy(css = ".banner-text p:nth-child(5) > a")
    public WebElement instagramLink;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "");
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        this.navigationPanel = new NavigationPanel(driver, wait);
    }

    public void hoverFirstProductInDrinks(){
        for (var i = 0; i < 12; i++){page.sendKeys(Keys.ARROW_DOWN);};
        new Actions(driver)
                .moveToElement(firstProductInDrinks)
                .perform();
    }

    public boolean isFirstProductInDrinksDisplayed(){
        wait.until(driver -> firstProductInDrinksInCartBtn
                .isDisplayed());
        return firstProductInDrinksInCartBtn.isDisplayed();
    }

    public boolean isNameProductContainsText(String nameProduct){
        return productName.getText().contains(nameProduct);
    }

    public void clickOnFirstDessert(){
        new Actions(driver)
                .moveToElement(firstDessert)
                .perform();
        firstDessert.click();
    }
    public void goFacebook(){
        wait.until(driver -> facebookLink
                .isDisplayed());
        new Actions(driver)
                .moveToElement(facebookLink)
                .perform();
        facebookLink.click();
    }

    public void goVK(){
        wait.until(driver -> vkLink
                .isDisplayed());
        new Actions(driver)
                .moveToElement(vkLink)
                .perform();
        vkLink.click();
    }

    public void goInstagram(){
        wait.until(driver -> instagramLink
                .isDisplayed());
        new Actions(driver)
                .moveToElement(instagramLink)
                .perform();
        instagramLink.click();
    }
}

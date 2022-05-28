package pages;

import org.openqa.selenium.By;
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

    @FindBy(id = "icon_facebook")
    public WebElement facebookLink;
    @FindBy(id = "icon_vk")
    public WebElement vkLink;
    @FindBy(id = "icon_twitter")
    public WebElement twitterLink;
    @FindBy(id = "icon_instagram")
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

    private By getSocialLinkLocator(int index){
        return By.cssSelector(String.format(".banner-text p:nth-child(%d)", index));
    }
    public void goToSocialLinks(int index){
        driver.findElement(getSocialLinkLocator(index)).click();
    }
}

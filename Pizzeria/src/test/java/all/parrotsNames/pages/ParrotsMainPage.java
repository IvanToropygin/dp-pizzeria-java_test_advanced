package all.parrotsNames.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParrotsMainPage {
    @FindBy(css = ".footer__contactItem:nth-of-type(2)")
    public WebElement skillboxLink;
    @FindBy(id = "form")
    public WebElement form;
    @FindBy(tagName = "iframe")
    private WebElement frame;
    @FindBy(css = "[name='email']")
    public WebElement emailInput;
    @FindBy(id = "sendMe")
    public WebElement pickUpNameBtn;
    @FindBy(className = "form-error")
    public WebElement errorInputEmail;
    @FindBy(className = "header")
    public WebElement header;
    @FindBy(id = "icon_facebook")
    public WebElement facebookLink;
    @FindBy(id = "icon_vk")
    public WebElement vkLink;
    @FindBy(id = "icon_twitter")
    public WebElement twitterLink;
    @FindBy(id = "icon_instagram")
    public WebElement instagramLink;
    @FindBy(className = "result-text")
    public WebElement successMessage;

    @FindBy(id = "anotherEmail")
    public WebElement anotherEmailLink;

    private WebDriver driver;
    private WebDriverWait wait;

    private String url = "http://qajava.skillbox.ru/module5/homework/";

    public ParrotsMainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void open(){driver.navigate().to(url);}

    public String getPageUrl(){return url;}

    public void switchToFrame(){driver.switchTo().frame(frame);}

    public void switchToParentFrame(){driver.switchTo().parentFrame();}

    public void switchToMaiPage(){driver.switchTo().defaultContent();}

    private By getSocialLinkLocator(int index){
        return By.cssSelector(String.format(".socialLinks__link:nth-of-type(%d)", index));
    }
    public void goToSocialLinks(int index){
        driver.findElement(getSocialLinkLocator(index)).click();
    }
}

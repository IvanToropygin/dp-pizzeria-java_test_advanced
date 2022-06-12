package DP.pizzeria.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PizzaPage extends Page{
    private WebDriver driver;
    private WebDriverWait wait;

    public NavigationPanel navigationPanel;

    @FindBy(className = "orderby")
    private WebElement select;
    @FindBy(css = ".wc-products li:first-child bdi")
    private WebElement firstPizzaPriceInSelection;
    @FindBy(css = ".wc-products li:last-child bdi")
    private WebElement lastPizzaPriceInSelection;

    @FindBy(css = ".ui-slider-handle:nth-of-type(1)")
    private WebElement leftFilterArrow;
    @FindBy(css = ".ui-slider-handle:nth-of-type(2)")
    private WebElement rightFilterArrow;

    @FindBy(css = ".price_label span:nth-of-type(2)")
    private WebElement rightPriceFilterArrow;
    @FindBy(css = ".price_label span:nth-of-type(1)")
    private WebElement leftPriceFilterArrow;

    @FindBy(css = ".wc-products li:first-child .add_to_cart_button")
    private WebElement addToCartFirstPizzaBtn;

    @FindBy(css = ".wc-products li:first-child .add_to_cart_button.loading")
    private WebElement loaderAddToCartFirstPizza;

    public PizzaPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "product-category/menu/pizza/");
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        this.navigationPanel = new NavigationPanel(driver, wait);
    }

    public void setSelect(String value) {
        select.click();
        var selectEnter = new Select(select);
        selectEnter.selectByVisibleText(value);
    }

    public String getSelect() {
        var selectEnter = new Select(select);
        return selectEnter.getFirstSelectedOption().getText();
    }

    public void moveRightArrowFilter(){
        new Actions(driver)
                .dragAndDrop(rightFilterArrow, leftFilterArrow)
                .perform();
    }

    public String getRightPriceFilter(){
        return rightPriceFilterArrow.getText();
    }
    public void moveLeftArrowFilter(){
        new Actions(driver)
                .dragAndDrop(leftFilterArrow, rightFilterArrow)
                .perform();
    }

    public String getLeftPriceFilter(){
        return leftPriceFilterArrow.getText();
    }

    public void addToCartFirstPizzaBtnClick() {
        addToCartFirstPizzaBtn.click();
        wait.until(driver -> driver.findElements(By.className(".loading")).size() == 0);
    }
}

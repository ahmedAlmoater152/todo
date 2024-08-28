package Pages;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class todosPage {

public WebDriver driver;
public Actions actions ;

    FluentWait wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(20))
            .pollingEvery(Duration.ofMillis(250))
            .ignoring(NoSuchElementException.class)
            .ignoring(NotFoundException.class)
            .ignoring(ElementNotInteractableException.class)
            .ignoring(StaleElementReferenceException.class);



public todosPage(WebDriver driver){this.driver=driver;}

    ////////// Locators /////////

    private By todosHeader = By.xpath("//h1[contains(text(),'todos')]");
    private By newtodoInput = By.xpath("//input[@class='new-todo']");

    private By payElectricLabel = By.xpath("//label[text()='Pay electric bill']");
    private By payElectricCheckbox = By.xpath("//label[text()='Pay electric bill']/preceding-sibling::input");

    private By todoList = By.xpath("//ul[@class='todo-list']");

    private By walkTheDogLabel = By.xpath("//label[text()='Walk the dog']");
    private By walkTheDogCheckbox = By.xpath("//label[text()='Walk the dog']/preceding-sibling::input");

//    private By todoli(String labelName){return By.xpath("//label[text()='"+labelName+"']/ancestor::li");}

    //completed checkbox xpath by labelName
    private By newAddedTodoCompletedCheckbox(String labelName){
        return By.xpath("//label[text()='"+labelName+"']/preceding-sibling::input");
    }

    //delete button xpath by labelName (you should hover over the checkbox)
    public By deleteTodoCheckbox(String labelName){
        return By.xpath("//label[text()='"+labelName+"']/following-sibling::button[@class='destroy todo-button']");
    }

    public By addedTodoLabel(String labelName){
        return By.xpath("//label[text()='"+labelName+"']");
    }

    private By itemsLeft = By.xpath("//span[@class='todo-count']/strong");
    private By allFilter = By.xpath("//ul/li/a[@href='#/']");
    private By activeFilter = By.xpath("//ul/li/a[@href='#/active']");
    private By completedFilter = By.xpath("//ul/li/a[@href='#/completed']");
    private By clearCompleatedButton = By.xpath("//button[contains(@class,'clear-completed')]");

    private By todoListElements = By.xpath("//ul[@class='todo-list']/child::li");


    //////////// Methods ////////////////

    public todosPage addNewToDo(String toDo){
        driver.findElement(newtodoInput).sendKeys(toDo + Keys.ENTER);
        return this;
    }

    public todosPage isToDoAdded(String toDo){
        wait.until(f->{
            driver.findElement(addedTodoLabel(toDo)).isDisplayed();
            return true;
        });
        return this;
    }

    public todosPage clickOnAllFilter(){
        wait.until(f->{
            driver.findElement(allFilter).click();
            return true;
        });
        return this;
    }

    public todosPage clickOnActiveFilter(){
        wait.until(f->{
            driver.findElement(activeFilter).click();
            return true;
        });
        return this;
    }

    public todosPage clickOnCompeletedFilter(){
        wait.until(f->{
            driver.findElement(completedFilter).click();
            return true;
        });
        return this;
    }

    public todosPage clickOnClearCompeletedButton(){
        wait.until(f->{
            driver.findElement(clearCompleatedButton).click();
            return true;
        });
        return this;
    }

    public int checkAllCompletedDeleted(){
       List<WebElement> completedList = driver.findElements(todoList);
        return completedList.size();
    }

    public todosPage checkAllCompletedDeletedx(){
        wait.until(f->{
            ExpectedConditions.attributeContains(completedFilter,"class","selected");
            return true;
        });
        List<WebElement> compeletedList = driver.findElements(todoList);
        Assert.assertEquals(compeletedList.size(),0);
        return this;
    }

    public todosPage clickOnDeleteToDo(String toDo){
        actions = new Actions(driver);
        WebElement elementToHover   = driver.findElement(addedTodoLabel(toDo));
        WebElement buttonAfterHover = driver.findElement(deleteTodoCheckbox(toDo));
        actions.moveToElement(elementToHover).moveToElement(buttonAfterHover).click().perform();
        return this;
    }

    public todosPage checkToDoIsDeleted(String toDo){
        wait.until(f->{
            ExpectedConditions.invisibilityOfElementLocated(addedTodoLabel(toDo));
            return true;
        });
        return this;
    }

    public todosPage makeToDoCompeleted(String todo){
        wait.until(f->{
            driver.findElement(newAddedTodoCompletedCheckbox(todo)).click();
            return true;
        });
        return this;
    }

//    public String getItemsLeft(){
//        return driver.findElement(itemsLeft).getText();
//    }
//

    public todosPage getItemsLeft(String expectedItemsLeft){
        wait.until(f->{
            Assert.assertEquals(driver.findElement(itemsLeft).getText(),expectedItemsLeft);
            return true;
        });
        return this;
    }

//    public todosPage isToDoCompeleted(String labelName){
//        Assert.assertEquals(todoli(labelName).getClass(),"completed");
//        return this;
//    }
}

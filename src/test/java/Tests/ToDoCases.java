package Tests;

import Pages.todosPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class ToDoCases {

    WebDriver driver;
    String baseURL = "https://example.cypress.io/todo";

    @Test
    public void addingNewToDo(){
        new todosPage(driver)
                .addNewToDo("test")
                .isToDoAdded("test");
    }

    @Test
    public void checkActiveItems(){
        new todosPage(driver)
                .addNewToDo("active")
                .isToDoAdded("active")
                .clickOnActiveFilter()
                .isToDoAdded("active");
    }

    @Test
    public void deleteToDo(){
        new todosPage(driver)
                .addNewToDo("to be deleted")
                .isToDoAdded("to be deleted")
                .clickOnDeleteToDo("to be deleted")
                .checkToDoIsDeleted("to be deleted");
    }

    @Test
    public void checkCompeletedToDo(){
        new todosPage(driver)
                .addNewToDo("compeleted")
                .isToDoAdded("compeleted")
                .makeToDoCompeleted("compeleted")
                .clickOnCompeletedFilter()
                .isToDoAdded("compeleted");
    }

    @Test
    public void checkItemsLeft(){
        new todosPage(driver)
                .addNewToDo("compeleted")
                .isToDoAdded("compeleted")
                .makeToDoCompeleted("compeleted")
                .makeToDoCompeleted("Walk the dog")
                .getItemsLeft("1");
    }

//    @Test
//    public void clearCompletedItems(){
//        new todosPage(driver)
//                .addNewToDo("compeleted")
//                .isToDoAdded("compeleted")
//                .makeToDoCompeleted("compeleted")
//                .makeToDoCompeleted("Walk the dog")
//                .clickOnClearCompeletedButton()
//                .clickOnCompeletedFilter()
//                .checkAllCompletedDeletedx();
//    }


    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}

package com.login_and_cart_package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Swaglab 
{
	WebDriver driver;
	WebDriverWait wait;
  @Test
  public static void setupDriver() 
  {
	  System.setProperty("webdriver.edge.driver", "C:\\Users\\shara\\Downloads\\edgedriver_win64");
  }
  
  @Test(priority=2)
  public void login_and_cart()
  {
	  Swaglab.setupDriver();
	  driver= new EdgeDriver();
	  driver.get("https://www.saucedemo.com/");
	  Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
	  driver.findElement(By.id("user-name")).sendKeys("standard_user");
	  driver.findElement(By.id("password")).sendKeys("secret_sauce");
	  driver.findElement(By.id("login-button")).click();
	  //Wait for the login to complete
	  wait = new WebDriverWait(driver, 10);
      wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
      //Check if login is successful
      Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
      //Sort the products in price low to high order
      WebElement sort = driver.findElement(By.className("product_sort_container"));
      sort.click();
      WebElement sortoptionLowToHigh = driver.findElement(By.xpath("//option[text()='Price (low to high)']"));
      sortoptionLowToHigh.click();
      //Add all items to the cart
      driver.findElements(By.xpath("//button[text()='Add to cart']")).forEach(WebElement::click);
      //Go to the Cart page and remove items that have a price <$15
      driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
      driver.findElements(By.xpath("//div[@class='inventory_item_price' and normalize-space(text())<'$15']//preceding::button[text()='R  emove']")).forEach(WebElement::click);
      //Click on the Checkout button
      driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
      //Enter the details on the information page.click on the Continue button
      driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("Sharath");
      driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("R");
      driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys("695036");
      driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
      //Finish the checkout by click on Finish button.
      driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();
      //Return to the home page
      driver.findElement(By.xpath("//*[@id=\"back-to-products\"]")).click();
      //Check if home page is displayed
      Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
      //Perform logout
      driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
      driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).click();
      //Check if logout is successful
      wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
      Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
      //Close the browser
      driver.quit();   
  }
  @Test(priority=1,enabled=true)
  public void login_validation()
  {
	  Swaglab.setupDriver();
	  driver= new EdgeDriver();
	  driver.get("https://www.saucedemo.com/");
	  Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
	  driver.findElement(By.id("login-button")).click();
	  String Validationmessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
	  System.out.println("Validationmessage");
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}

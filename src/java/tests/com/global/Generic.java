package com.global;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class Generic {

	public boolean isElementPresent(WebDriver driver, By elem) {

		try {

			driver.findElement(elem);
			return true;

		}catch(NoSuchElementException e) {

			return false;
		}


	}
	
	public static void waitFor(WebDriver driver, ExpectedCondition<?> condition,int timeoutSeconds) throws Exception {
	    
	    try {
	        (new WebDriverWait(driver, timeoutSeconds)).until(condition);
	    } finally {
	    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    }
	}
	
	

	public WebElement waitFor(WebDriver driver, WebElement elem, String waitType, long waitInSeconds ) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(waitInSeconds, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		try{
			switch(waitType) 
			{
			case "elementToBeClickable":
				wait.until(ExpectedConditions.elementToBeClickable(elem));
				break;

			case "visibilityOf":
				wait.until(ExpectedConditions.visibilityOf(elem));
				break;

			case "elementToBeSelected":
				wait.until(ExpectedConditions.elementToBeSelected(elem));
				break;

			default:
				wait.until(ExpectedConditions.visibilityOf(elem));
				break;

			}

		}
		catch(TimeoutException e) {

			e.printStackTrace();

		}

		return elem;
	}


	public void embedScreenshot(WebDriver driver, Scenario scenario) {
		try 
		{
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		} 
		catch(WebDriverException somePlatformsDontSupportScreenshots)
		{     
			System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		}
		catch(Exception e) 
		{
			System.err.println("Error occurred during taking snapshot" + e.getMessage());

		}

	}


}

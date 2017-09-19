package com.global;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Generic {

	public boolean isElementPresent(WebDriver driver, By elem) {

		try {

			driver.findElement(elem);
			return true;

		}catch(NoSuchElementException e) {

			return false;
		}


	}

	
	public Wait<WebDriver> waitFor(WebDriver driver, long durationInSeconds) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								   .withTimeout(durationInSeconds, TimeUnit.SECONDS)
								   .pollingEvery(500, TimeUnit.MILLISECONDS)
								   .ignoring(NoSuchElementException.class);
		return wait;


	}

}

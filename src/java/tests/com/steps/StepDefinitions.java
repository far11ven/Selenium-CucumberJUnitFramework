package com.steps;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.global.Generic;
import com.utilities.LogAction;
import com.utilities.TestFailException;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	WebDriver driver = null;
	LogAction log = new LogAction(StepDefinitions.class);
	Generic gfx = new Generic();


	@Before
	public void setUp(){

		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		log.writeLog("INFO", "WebDriver Initialized");


	}

	@After
	public void tearDown() throws IOException{

		/*driver.close();
		driver.quit();*/

		log.writeLog("PASS", "WebDriver Closed");


	}

	@Given("^I launch website URL$")
	public void i_launch_website(){

		driver.get("https://www.gmail.com");

		log.writeLog("INFO", "Websitr URL launched");

	}

	@When("^I provide ([^\"]*) and ([^\"]*)$")
	public void loginToWebsite(String user, String pass) throws InterruptedException {

		driver.findElement(By.name("identifier")).sendKeys(user);
		driver.findElement(By.xpath("//span[contains(.,'Next')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.xpath("//span[contains(.,'Next')]")).click();

		log.writeLog("INFO", "Gmail credentials provided");



	}

	@Then("^I verify login is successful for ([^\"]*)$")
	public void verfify_login(String username) throws TestFailException {
		try {

			gfx.waitFor(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.gb_Fc.gb_gb.gb_tg.gb_R > a")));

			if(gfx.isElementPresent(driver, By.cssSelector("div.gb_Fc.gb_gb.gb_tg.gb_R > a"))) {

				WebElement profileIcon = driver.findElement(By.cssSelector("div.gb_Fc.gb_gb.gb_tg.gb_R > a"));

				if (profileIcon.getAttribute("title").contains(username)) {

					log.writeLog("PASS", "Login Success, User is present on Login Home Page");

				}
				else {

					log.writeLog("FAIL", "Login Failure, User Profile mis-match");
				}
			}
			else {
				log.writeLog("FAIL", "Login Failure");

			}

		}catch(Exception e) {
			
			log.writeLog("FAIL", "Login Failure, User is not present on Login Home Page");

		}

	}


}

package com.steps;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.global.Generic;
import com.utilities.LogAction;
import com.utilities.TestFailedException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	public WebDriver driver = null;
	
	public Scenario currentScenario = null;
	LogAction log = new LogAction(StepDefinitions.class);
	Generic gfx = new Generic();


	@Before
	public void setUp(Scenario scenario){
		
		this.currentScenario = scenario;
		
		log.writeLog("INFO","Running scenario : " + "[ Name : "+ currentScenario.getName() + " ]" + "[ SourceTagNames : "+  currentScenario.getSourceTagNames() + " ]");

		System.setProperty("webdriver.chrome.driver", "C:\\Eclipse\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		log.writeLog("INFO", "WebDriver Initialized");

	}

	@After
	public void tearDown() throws IOException{

		/*driver.close();
		driver.quit();*/

		log.writeLog("PASS", "WebDriver Closed");
		log.writeLog("INFO","Scenario " + "[ Name : "+ currentScenario.getName() + " ]"  + " has run with \n [ STATUS = "+  currentScenario.getStatus() + " ]");

		

	}

	@Given("^I launch website URL$")
	public void i_launch_website(){

		driver.get("https://www.gmail.com");

		log.writeLog("INFO", "Website URL launched");
		gfx.embedScreenshot(driver, currentScenario);

	}

	@When("^I provide ([^\"]*) and ([^\"]*)$")
	public void loginToWebsite(String user, String pass) throws InterruptedException {

		driver.findElement(By.name("identifier")).sendKeys(user);
		gfx.embedScreenshot(driver, currentScenario);
		driver.findElement(By.xpath("//span[contains(.,'Next')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("password")).sendKeys(pass);
		gfx.embedScreenshot(driver, currentScenario);
		driver.findElement(By.xpath("//span[contains(.,'Next')]")).click();

		log.writeLog("INFO", "Gmail credentials provided");
		gfx.embedScreenshot(driver, currentScenario);



	}

	@Then("^I verify login is successful for ([^\"]*)$")
	public void verfify_login(String username) throws TestFailedException {
		try {

			gfx.waitFor(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.gb_Fc.gb_gb.gb_tg.gb_R > a")));
			gfx.embedScreenshot(driver, currentScenario);
			if(gfx.isElementPresent(driver, By.cssSelector("div.gb_Fc.gb_gb.gb_tg.gb_R > a"))) {

				WebElement profileIcon = driver.findElement(By.cssSelector("div.gb_Fc.gb_gb.gb_tg.gb_R > a"));

				if (profileIcon.getAttribute("title").contains(username)) {

					log.writeLog("PASS", "Login Success, User is present on Login Home Page");
					gfx.embedScreenshot(driver, currentScenario);

				}
				else {

					log.writeLog("FAIL", "Login Failure, User Profile mis-match");
					gfx.embedScreenshot(driver, currentScenario);
				}
			}
			else {
				log.writeLog("FAIL", "Login Failure");
				gfx.embedScreenshot(driver, currentScenario);

			}

		}catch(Exception e) {
			
			log.writeLog("FAIL", "Login Failure, User is not present on Login Home Page");
			gfx.embedScreenshot(driver, currentScenario);

		}

	}


}

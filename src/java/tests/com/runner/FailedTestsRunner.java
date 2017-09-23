package com.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/html-report", "json:target/json-report.json", "rerun:target/rerun.txt" },
		monochrome=true,
		glue= {"com.steps"},
		features = "@target/rerun.txt" //Cucumber picks the failed scenarios from this file 

		)


public class FailedTestsRunner {
	
	

}
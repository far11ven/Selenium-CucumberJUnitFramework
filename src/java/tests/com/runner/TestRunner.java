package com.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		plugin = {"pretty", "html:target/html-report", "json:target/json-report.json", "rerun:target/rerun.txt" },
		monochrome=true,
		features="./src/java/features/feature1.feature",
		glue= {"com.steps"},
		tags="@test2")

public class TestRunner {

}

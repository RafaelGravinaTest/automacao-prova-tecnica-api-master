package tests;

import Utils.TestRule;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@teste", glue = {"Utils","steps"}, monochrome = true, dryRun = false, plugin = {})
public class LocalTest {

	@ClassRule
	public static TestRule testRule = new TestRule();
	
}
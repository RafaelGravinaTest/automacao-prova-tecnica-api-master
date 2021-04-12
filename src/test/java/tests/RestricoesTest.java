package tests;

import Utils.TestRule;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@Restricoes", glue = {"Utils", "steps"}, monochrome = true, dryRun = false, plugin = {})
public class RestricoesTest {

	@ClassRule
	public static TestRule testRule = new TestRule();

}
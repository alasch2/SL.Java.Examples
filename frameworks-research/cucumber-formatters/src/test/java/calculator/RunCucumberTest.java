package calculator;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import tia.TIA;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"json:target/cucumber-report.json", "formatter.SealightsListener"},
		features = "src/test/resources/features",
		tags= {"not "+ TIA.SKIPPED_BY_TIA} // cuc-5 format
//		tags= "not "+ TIA.SKIPPED_BY_TIA // cuc-6 format
		)
public class RunCucumberTest {

}

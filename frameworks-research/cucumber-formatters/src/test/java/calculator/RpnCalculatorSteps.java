package calculator;

import java.io.File;
import java.util.List;

import org.junit.Assume;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tia.TIA;

public class RpnCalculatorSteps {
    private RpnCalculator calc;

    @Given("a calculator I just turned on")
    public void a_calculator_I_just_turned_on() {
        calc = new RpnCalculator();
    }

    @When("I add {int} and {int}")
    public void adding(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @Given("^I press (.+)$")
    public void I_press(String what) {
        calc.push(what);
    }

    @Then("the result is {int}")
    public void the_result_is(double expected) {
        assertEquals(expected, calc.value());
    }

//    @Before("not @foo")
//    public void beforeNotFoo(Scenario scenario) {
//    	System.out.println(String.format("before not foo scenario [%s]", toString(scenario)));
//    }
//
//    @Before("@foo")
//    public void beforeFoo(Scenario scenario) {
//    	System.out.println(String.format("before foo scenario [%s]", toString(scenario)));
//    }
//
    @Before()
    public void beforeAny(Scenario scenario) {
    	System.out.println(String.format("before any scenario [%s]", toString(scenario)));
//		String testName = String.format("%s#%s", new File(scenario.getUri().getPath()).getName(), scenario.getName());
//		if (TIA.toSkip(testName)) {
//			Assume.assumeTrue(false);
//			System.out.println("Skipping " + testName);
//		}
    }

    @After
    public void after(Scenario scenario) {
        // scenario.write("HELLLLOO");
    	System.out.println(String.format("after scenario [%s]", toString(scenario)));
    }
    
    private String toString(Scenario scenario) {
    	return String.format("scenario %s.%s, status:%s", scenario.getName(), scenario.getId(), scenario.getStatus());
    }

    @Given("the previous entries:")
    public void thePreviousEntries(List<Entry> entries) {
        for (Entry entry : entries) {
            calc.push(entry.first);
            calc.push(entry.second);
            calc.push(entry.operation);
        }
    }

    static final class Entry {
        private Integer first;
        private Integer second;
        private String operation;

        public Integer getFirst() {
            return first;
        }

        public void setFirst(Integer first) {
            this.first = first;
        }

        public Integer getSecond() {
            return second;
        }

        public void setSecond(Integer second) {
            this.second = second;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }
    }
}

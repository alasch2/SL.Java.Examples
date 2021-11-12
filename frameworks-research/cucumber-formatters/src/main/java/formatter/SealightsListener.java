package formatter;

import java.io.File;

import org.junit.Assume;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.Event;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import tia.TIA;

public class SealightsListener implements ConcurrentEventListener {

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestRunStarted.class, new TestRunStartedHandler());
		publisher.registerHandlerFor(TestRunFinished.class, new BaseEventHandler<TestRunFinished>());
		publisher.registerHandlerFor(TestCaseStarted.class, new TestCaseStartedHandler());
		publisher.registerHandlerFor(TestCaseFinished.class, new BaseEventHandler<TestCaseFinished>());
	}
	
	static String buildTestName(TestCase testCase) {
		return String.format("%s#%s", new File(testCase.getUri().getPath()).getName(), testCase.getName());
	}
	
	static class TestRunStartedHandler extends BaseEventHandler<TestRunStarted> {

		@Override
		public void receive(TestRunStarted event) {
			super.receive(event);
	        TIA.addExcludedMethod("basic_arithmetic.feature#Many additions");
		}
		
	}
	
	static class TestCaseStartedHandler extends BaseEventHandler<TestCaseStarted> {

		@Override
		public void receive(TestCaseStarted event) {
			super.receive(event);
			String testName = buildTestName(event.getTestCase());
			if (TIA.toSkip(testName)) {
				event.getTestCase().getTags().add(TIA.SKIPPED_BY_TIA);
				System.out.println("try to skip test " + testName);
			}
		}
	}
	
	static class BaseEventHandler<T extends Event> implements EventHandler<T> {

		@Override
		public void receive(T event) {
			System.out.println("recieved " + event.getClass().getSimpleName() + " ...");			
		}
		
	}
}

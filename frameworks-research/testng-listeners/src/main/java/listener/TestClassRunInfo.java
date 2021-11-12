package listener;

import org.testng.ITestResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestClassRunInfo {
	private String name;
	private TestClassRunResult testClassRunResult;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TestClassRunResult {
		private long started;
		private long ended;
		private int status;
		
		public TestClassRunResult(long started) {
			this.started = started;
		}
		
		public void setEndResult(long methodEnded, int methodResult) {
			// the last end is relevant
			ended = Math.max(methodEnded, ended);
			// If status not initialized (=0), replace it. Else success (1) is overwrote by any failure
			switch (methodResult) {
			case ITestResult.FAILURE:
			case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
				status = ITestResult.FAILURE;
				break;
			case ITestResult.SUCCESS:
				// class failed if at least one method failed 
				if (status != ITestResult.FAILURE) {
					status = methodResult;
				}
				break;
			}
		}
		
	}
}
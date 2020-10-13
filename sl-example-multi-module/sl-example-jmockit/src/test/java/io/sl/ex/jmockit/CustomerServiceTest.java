package io.sl.ex.jmockit;

import org.junit.Test;

import io.sl.ex.jmockit.CustomerService.Customer;
import io.sl.ex.jmockit.CustomerService.CustomerDao;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class CustomerServiceTest {

	@Injectable CustomerDao dao;
	
	@Tested CustomerService service;

	@Test
	public void testDoBusiness(@Mocked final Customer customer) {
		final String FAKE_NAME = "fake name";
	    new Expectations() {{
	    	customer.getName(); result = FAKE_NAME;
	    	dao.handle(FAKE_NAME); result = true;
	    	
	    }};
	    service.doBusiness(customer);
	    new Verifications() {{
	    	dao.appyFlag(true);
	    }};
	}
}

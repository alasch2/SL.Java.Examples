package io.sl.ex.jmockit;

public class CustomerService {
    private CustomerDao dao;

    public void doBusiness(Customer customer) {
        boolean value = dao.handle(customer.getName());
        dao.appyFlag(value);
    }

    public static class CustomerDao {
    	public boolean handle(String value) {
    		return false;
    	}
    	
    	public void appyFlag(boolean flag) {    		
    	}
    }
    
    public static class Customer {
    	public String getName() {
    		return "name";
    	}
    }
}
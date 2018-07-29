package io.sl.ex.junit.iface;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fufu2 {
	static Logger LOG = LoggerFactory.getLogger(Fufu2.class);
    private int foo;

    public void kuku() {
    	LOG.info("Log something");
        this.foo = 0;
    }

    public void setFoo(int foo) {
        this.foo = foo;
    }

    public int getFoo() {
        return foo;
    }
}

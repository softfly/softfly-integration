package pl.softfly.oipf.samples.invoice.processes.ejb;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class Resources {

    @Produces
    public Test1 dd() {
    	Test1 test1 = new Test1();
    	test1.txt="produce";
		return test1;
    }
	
}

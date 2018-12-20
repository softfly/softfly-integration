package pl.softfly.oipf.samples.invoice.processes.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@Startup
@Singleton
@LocalBean
public class Test2 {
	
	@Inject
	Test1 test1;
	
    @PostConstruct 
    void atStartup() {
    	System.out.println(test1.getTxt());
    }
    
}

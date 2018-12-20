package pl.softfly.opif.samples.invoice.jbpm.ejb;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;

@ApplicationScoped
public class EnvironmentProducer {
	
    @Produces
    @Default
    public RuntimeManager produceRuntimeManager() {
    	System.out.println("build env");
    	RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
                .newDefaultBuilder();
        
		return RuntimeManagerFactory.Factory.get()
				.newSingletonRuntimeManager(builder.get(), "pl.softfly.opif.samples.invoice.jbpm:ejb:1.0");
    }

}

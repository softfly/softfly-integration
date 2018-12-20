package pl.softfly.opif.samples.invoice.jbpm.ejb;



import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;

@Named
@ApplicationScoped
public class EnvironmentProducer {
	
    @Produces
    @Default
    //@Singleton
    public RuntimeEnvironment produceEnvironment() {

        RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory.get()
                .newDefaultBuilder()
                .get();
        
        return environment;
    }

}

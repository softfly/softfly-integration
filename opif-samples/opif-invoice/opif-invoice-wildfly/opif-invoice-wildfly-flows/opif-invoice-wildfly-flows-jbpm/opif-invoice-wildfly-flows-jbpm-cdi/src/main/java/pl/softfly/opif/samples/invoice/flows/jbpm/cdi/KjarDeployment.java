package pl.softfly.opif.samples.invoice.flows.jbpm.cdi;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.model.DeploymentUnit;
import org.jbpm.services.cdi.Kjar;
import org.kie.internal.runtime.cdi.BootOnLoad;

@ApplicationScoped
@BootOnLoad
public class KjarDeployment {
	
	public static final String DEPLOYMENT_ID = "pl.softfly.opif.samples.invoice:opif-invoice-wildfly-flows-jbpm-kjar:1.0-SNAPSHOT";
	
    @Inject
    @Kjar
    DeploymentService deploymentService;
    
    @PostConstruct
    public void init() {
        String[] gav = DEPLOYMENT_ID.split(":");
        DeploymentUnit deploymentUnit = new KModuleDeploymentUnit(gav[0], gav[1], gav[2]);
        deploymentService.deploy(deploymentUnit);

    	System.out.println(deploymentService.getDeployedUnits());
    }

}

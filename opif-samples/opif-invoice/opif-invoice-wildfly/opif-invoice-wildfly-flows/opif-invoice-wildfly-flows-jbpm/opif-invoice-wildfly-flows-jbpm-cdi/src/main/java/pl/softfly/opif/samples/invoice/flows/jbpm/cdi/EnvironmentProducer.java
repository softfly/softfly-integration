package pl.softfly.opif.samples.invoice.flows.jbpm.cdi;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.cdi.Kjar;
import org.jbpm.services.cdi.Selectable;
import org.jbpm.services.cdi.producer.UserGroupInfoProducer;
import org.jbpm.services.task.audit.JPATaskLifeCycleEventListener;
import org.jbpm.services.task.lifecycle.listeners.TaskLifeCycleEventListener;
import org.kie.api.task.UserGroupCallback;
import org.kie.internal.identity.IdentityProvider;

@ApplicationScoped
public class EnvironmentProducer {
	
	@PersistenceUnit(unitName = "org.jbpm.domain")
    private EntityManagerFactory emf;
    
    @Inject
    @Selectable
    private UserGroupInfoProducer userGroupInfoProducer;
    
    @Inject
    @Kjar
    DeploymentService deploymentService;
    
    @Produces
    public EntityManagerFactory getEntityManagerFactory() {
        return this.emf;
    }
    
    @Produces
    @Default
    public DeploymentService getDeploymentService() {
        return this.deploymentService;
    }
    
    @Produces
    public UserGroupCallback produceSelectedUserGroupCalback() {
        return userGroupInfoProducer.produceCallback();
    }
    
    @Produces
    public IdentityProvider produceIdentityProvider() {
        return new IdentityProvider() {

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<String> getRoles() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean hasRole(String role) {
				// TODO Auto-generated method stub
				return false;
			}
             // implement IdentityProvider
        };
    }
    
    @Produces
    public TaskLifeCycleEventListener produceAuditListener() {
    	return new JPATaskLifeCycleEventListener(true);
    }

}

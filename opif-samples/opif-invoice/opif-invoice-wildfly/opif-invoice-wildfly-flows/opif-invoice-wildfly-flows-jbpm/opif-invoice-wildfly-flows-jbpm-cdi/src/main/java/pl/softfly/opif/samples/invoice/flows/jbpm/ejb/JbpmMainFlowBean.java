package pl.softfly.opif.samples.invoice.flows.jbpm.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.model.DeploymentUnit;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.context.EmptyContext;

@Startup
@javax.ejb.Singleton
@LocalBean
public class JbpmMainFlowBean {
	
	/*
	@Inject
	ProcessService processService;

	@PostConstruct
	public void startProcess() {
		long processInstanceId = -1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("recipient", null);
		processInstanceId = processService.startProcess(KjarDeployment.DEPLOYMENT_ID,
				"pl.softfly.opif.samples.invoice.process.jbpm.MainFlow", params);
	}*/

	
	//@Inject
	//@Singleton
	//private RuntimeManager singletonManager = produceRuntimeManager();
	
	/*
    public RuntimeManager produceRuntimeManager() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieBase kbase = kContainer.getKieBase("defaultKieBase");
		
    	RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
                .newEmptyBuilder()
                .persistence(false)
                .knowledgeBase(kbase);
        
		return RuntimeManagerFactory.Factory.get()
				.newSingletonRuntimeManager(builder.get(), "pl.softfly.opif.samples.invoice.jbpm:ejb:1.0");
    }*/

}

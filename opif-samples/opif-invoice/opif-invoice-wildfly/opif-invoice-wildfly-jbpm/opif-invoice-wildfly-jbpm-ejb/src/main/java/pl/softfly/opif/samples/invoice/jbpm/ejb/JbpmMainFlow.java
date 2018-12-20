package pl.softfly.opif.samples.invoice.jbpm.ejb;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;

@ApplicationScoped
public class JbpmMainFlow {

	//@Inject
	//@Singleton
	private RuntimeManager singletonManager;

	public void startProcess() {
		RuntimeEngine runtime = singletonManager.getRuntimeEngine(null);
		KieSession ksession = runtime.getKieSession();
		ProcessInstance processInstance = ksession.startProcess("pl.softfly.opif.samples.invoice.process.jbpm.MainFlow");
		singletonManager.disposeRuntimeEngine(runtime);
	}

}

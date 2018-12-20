package pl.softfly.opif.samples.invoice.jbpm.ejb;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.task.TaskService;
import pl.softfly.opif.samples.invoice.jbpm.workitemhandler.DocumentBusinessValidationWorkItemHandler;
import pl.softfly.opif.samples.invoice.jbpm.workitemhandler.DocumentParserWorkItemHandler;
import pl.softfly.opif.samples.invoice.jbpm.workitemhandler.DocumentRecognizeWorkItemHandler;
import pl.softfly.opif.samples.invoice.jbpm.workitemhandler.DocumentSendWorkItemHandler;
import pl.softfly.opif.samples.invoice.jbpm.workitemhandler.DocumentValidationSchemeWorkItemHandler;
import pl.softfly.opif.samples.invoice.jbpm.workitemhandler.TransformStringToDocumentWorkItemHandler;

public class ProcessMain {

	public static void main(String[] args) {
		System.setProperty("org.kie.deployment.desc.location", "classpath:META-INF/kie-deployment-descriptor.xml");
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieBase kbase = kContainer.getKieBase("defaultKieBase");

		RuntimeManager manager = createRuntimeManager(kbase);
		RuntimeEngine engine = manager.getRuntimeEngine(null);
        KieSession ksession = engine.getKieSession();
        
        WorkItemManager workItemManager = ksession.getWorkItemManager();
        workItemManager.registerWorkItemHandler(
                "TransformStringToDocument", new TransformStringToDocumentWorkItemHandler()
        );
        workItemManager.registerWorkItemHandler(
                "DocumentRecognize", new DocumentRecognizeWorkItemHandler()
        );
        workItemManager.registerWorkItemHandler(
                "DocumentValidationScheme", new DocumentValidationSchemeWorkItemHandler()
        );
        workItemManager.registerWorkItemHandler(
                "DocumentBusinessValidation", new DocumentBusinessValidationWorkItemHandler()
        );
        workItemManager.registerWorkItemHandler(
                "DocumentParser", new DocumentParserWorkItemHandler()
        );
        workItemManager.registerWorkItemHandler(
                "DocumentSend", new DocumentSendWorkItemHandler()
        );
		
		ksession.startProcess("pl.softfly.opif.samples.invoice.process.jbpm.MainFlow");

		manager.disposeRuntimeEngine(engine);
		System.exit(0);
	}

	private static RuntimeManager createRuntimeManager(KieBase kbase) {
		//org.kie.api.task.TaskService
		
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
			.newEmptyBuilder()
			.persistence(false)
			.knowledgeBase(kbase);
			//.addAsset(ResourceFactory.newClassPathResource("pl/softfly/opif/samples/invoice/process/jbpm/MainFlow.bpmn2"), ResourceType.BPMN2);
		
		return RuntimeManagerFactory.Factory.get()
			.newSingletonRuntimeManager(builder.get(), "pl.softfly.opif.samples.invoice.jbpm:ejb:1.0");
	}

}
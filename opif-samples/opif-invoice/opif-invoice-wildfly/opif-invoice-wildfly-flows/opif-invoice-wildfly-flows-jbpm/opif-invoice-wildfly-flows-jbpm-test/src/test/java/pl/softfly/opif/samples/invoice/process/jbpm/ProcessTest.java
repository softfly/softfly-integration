package pl.softfly.opif.samples.invoice.process.jbpm;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.task.TaskService;
import java.io.IOException;
import java.util.HashMap;

/**
 * This is a sample file to launch a process.
 */
public class ProcessTest extends JbpmJUnitBaseTestCase {


    public ProcessTest() {
        super(true, true);
    }

    @Test
    public void testProcess() throws IOException {
		/*Enumeration<URL> url = getClass().getClassLoader().getResources("MainFlow.bpmn2");
		System.out.println(url);
		System.exit(0);*/
        RuntimeManager manager = createRuntimeManager("MainFlow.bpmn2");


        RuntimeEngine engine = getRuntimeEngine(null);
        KieSession ksession = engine.getKieSession();
        TaskService taskService = engine.getTaskService();

        /*
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
        );*/

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("Parameter", "testMessage");
        ProcessInstance processInstance = ksession.startProcess("pl.softfly.opif.samples.invoice.process.jbpm.MainFlow", parameters);
		/*
		assertProcessInstanceActive(processInstance.getId(), ksession);
		assertNodeTriggered(processInstance.getId(), "Task 1");

		// let john execute Task 1
		List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner("john", "en-UK");
		TaskSummary task = list.get(0);
		System.out.println("John is executing task " + task.getName());
		taskService.start(task.getId(), "john");
		taskService.complete(task.getId(), "john", null);

		assertNodeTriggered(processInstance.getId(), "Task 2");

		// let mary execute Task 2
		list = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");
		task = list.get(0);
		System.out.println("Mary is executing task " + task.getName());
		taskService.start(task.getId(), "mary");
		taskService.complete(task.getId(), "mary", null);*/

        assertProcessInstanceCompleted(processInstance.getId(), ksession);
        assertNodeTriggered(processInstance.getId(), "Task 1");

        manager.disposeRuntimeEngine(engine);
        manager.close();
    }

}
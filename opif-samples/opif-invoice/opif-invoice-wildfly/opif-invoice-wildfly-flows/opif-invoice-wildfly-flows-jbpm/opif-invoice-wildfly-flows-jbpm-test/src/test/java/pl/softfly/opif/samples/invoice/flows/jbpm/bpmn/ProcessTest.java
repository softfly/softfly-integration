package pl.softfly.opif.samples.invoice.flows.jbpm.bpmn;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.jbpm.workflow.instance.impl.WorkflowProcessInstanceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.task.TaskService;

import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.DocumentBusinessValidationWorkItemHandler;
import pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.DocumentFormatFindSupportedWorkItemHandler;
import pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.DocumentParserWorkItemHandler;
import pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.DocumentRecognizeWorkItemHandler;
import pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.DocumentSendWorkItemHandler;
import pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.DocumentTransformWorkItemHandler;
import pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.DocumentValidationSchemeWorkItemHandler;
import pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.TransformStringToDocumentWorkItemHandler;
import pl.softfly.samples.invoice.entity.InvoiceDocumentStatus;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a sample file to launch a process.
 */
public class ProcessTest extends JbpmJUnitBaseTestCase {
	
	protected final static String PARAMETER = "Parameter";
	
	protected final static String MAIN_FLOW = "pl.softfly.opif.samples.invoice.flows.jbpm.bpmn.MainFlow";
	
	protected final static String DOCUMENT_HEADER = "DocumentHeader";

    protected RuntimeEngine engine;
    
    protected KieSession ksession;
	
    public ProcessTest() {
        super(true, true);
    }
    
    @Before
    public void produceRuntimeManager() {
    	RuntimeManager manager = createRuntimeManager("pl/softfly/opif/samples/invoice/flows/jbpm/bpmn/MainFlow.bpmn2");
    	
        engine = getRuntimeEngine(null);
        ksession = engine.getKieSession();
        TaskService taskService = engine.getTaskService();
        
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
                "FindSupportedDocumentFormats", new DocumentFormatFindSupportedWorkItemHandler()
        );
        workItemManager.registerWorkItemHandler(
                "TransformDocument", new DocumentTransformWorkItemHandler()
        );
        workItemManager.registerWorkItemHandler(
                "DocumentSend", new DocumentSendWorkItemHandler()
        );
    }
    
    @After
    public void afterClass() throws IOException {
    	RuntimeEngine engine = getRuntimeEngine(null);
        manager.disposeRuntimeEngine(engine);
        manager.close();
    }
    
    @Test
    public void testNotRecognized() throws IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(PARAMETER, "NotRecognized");
        ProcessInstance processInstance = ksession.startProcess(MAIN_FLOW, parameters);
        
        assertProcessInstanceAborted(processInstance.getId());
        WorkflowProcessInstanceImpl instance = (WorkflowProcessInstanceImpl) processInstance;
        
        parameters = instance.getVariables();
        DocumentHeader header = (DocumentHeader) parameters.get(DOCUMENT_HEADER);
               
        assertEquals(InvoiceDocumentStatus.NOT_RECOGNIZED, header.getStatus());
    }
    
    @Test
    public void testNotSchemeValidated() throws IOException {        
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(PARAMETER, "NotSchemeValidated");
        ProcessInstance processInstance = ksession.startProcess(MAIN_FLOW, parameters);
        
        assertProcessInstanceAborted(processInstance.getId());
        WorkflowProcessInstanceImpl instance = (WorkflowProcessInstanceImpl) processInstance;
        
        parameters = instance.getVariables();
        DocumentHeader header = (DocumentHeader) parameters.get(DOCUMENT_HEADER);
               
        assertEquals(InvoiceDocumentStatus.NOT_SCHEME_VALIDATED, header.getStatus());
    }
    
    @Test
    public void testNotBusinessValidated() throws IOException {        
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(PARAMETER, "NotBusinessValidated");
        ProcessInstance processInstance = ksession.startProcess(MAIN_FLOW, parameters);
        
        assertProcessInstanceAborted(processInstance.getId());
        WorkflowProcessInstanceImpl instance = (WorkflowProcessInstanceImpl) processInstance;
        
        parameters = instance.getVariables();
        DocumentHeader header = (DocumentHeader) parameters.get(DOCUMENT_HEADER);
               
        assertEquals(InvoiceDocumentStatus.NOT_BUSINESS_VALIDATED, header.getStatus());
    }
    
    @Test
    public void testSubmitted() throws IOException {        
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(PARAMETER, "Submitted");
        ProcessInstance processInstance = ksession.startProcess(MAIN_FLOW, parameters);
        
        assertProcessInstanceCompleted(processInstance.getId());
        WorkflowProcessInstanceImpl instance = (WorkflowProcessInstanceImpl) processInstance;
        
        parameters = instance.getVariables();
        DocumentHeader header = (DocumentHeader) parameters.get(DOCUMENT_HEADER);
               
        assertEquals(InvoiceDocumentStatus.SUBMITTED, header.getStatus());
    }

}
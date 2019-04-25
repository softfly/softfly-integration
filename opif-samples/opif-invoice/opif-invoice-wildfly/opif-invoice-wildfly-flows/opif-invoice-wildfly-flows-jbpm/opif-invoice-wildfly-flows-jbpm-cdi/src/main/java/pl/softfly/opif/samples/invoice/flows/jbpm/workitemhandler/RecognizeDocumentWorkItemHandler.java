package pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler;

import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import pl.softfly.oipf.document.recognize.DocumentRecognizeBean;
import pl.softfly.oipf.document.recognize.DocumentRecognizeLocalBean;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.samples.invoice.entity.InvoiceDocumentStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RecognizeDocumentWorkItemHandler extends BaseWorkItemHandler implements WorkItemHandler {

    DocumentRecognizeBean documentRecognize = new DocumentRecognizeBean();
    
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
    	//INPUT
        DocumentHeader documentHeader = (DocumentHeader) workItem.getParameter(WorkItemProperties.DOCUMENT_HEADER_INPUT);
        Objects.requireNonNull(documentHeader);
        
        //EXECUTE
        boolean isRecognize = DocumentRecognizeLocalBean.enrichRecognize(documentRecognize, documentHeader);
		documentHeader.setStatus(isRecognize ? InvoiceDocumentStatus.RECOGNIZED : InvoiceDocumentStatus.NOT_RECOGNIZED);

        //OUTPUT
        Map<String, Object> results = workItem.getResults();
        results.put(WorkItemProperties.DOCUMENT_HEADER_RESULT, documentHeader);
        results.put(WorkItemProperties.RETURN, isRecognize);
        manager.completeWorkItem(workItem.getId(), results);
    }
    
}

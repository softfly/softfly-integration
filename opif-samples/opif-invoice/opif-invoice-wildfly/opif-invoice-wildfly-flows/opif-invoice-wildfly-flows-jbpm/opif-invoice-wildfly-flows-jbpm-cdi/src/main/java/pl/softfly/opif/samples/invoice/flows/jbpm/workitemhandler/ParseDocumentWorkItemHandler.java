package pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler;

import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import pl.softfly.oipf.document.parser.DocumentParserBean;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.samples.invoice.entity.InvoiceDocumentStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParseDocumentWorkItemHandler extends BaseWorkItemHandler implements WorkItemHandler {

    DocumentParserBean documentParserBean = new DocumentParserBean();

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
    	//INPUT
        DocumentHeader documentHeader = (DocumentHeader) workItem.getParameter(WorkItemProperties.DOCUMENT_HEADER_INPUT);
        Objects.requireNonNull(documentHeader);
        
        //EXECUTE
        documentParserBean.parse(documentHeader);
        documentHeader.setStatus(InvoiceDocumentStatus.PARSED);
        
        //OUTPUT
        Map<String, Object> results = workItem.getResults();
        results.put(WorkItemProperties.DOCUMENT_HEADER_RESULT, documentHeader);
        manager.completeWorkItem(workItem.getId(), results);
    }

}

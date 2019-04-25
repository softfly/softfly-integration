package pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler;

import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.opif.document.repo.DocumentRepository;
import pl.softfly.opif.document.repo.DocumentRepositoryBean;
import pl.softfly.samples.invoice.entity.InvoiceDocumentStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateDocumentHeaderWorkItemHandler extends BaseWorkItemHandler implements WorkItemHandler {

	DocumentRepository documentRepository = new DocumentRepositoryBean();

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
    	//INPUT
        String input = (String) workItem.getParameter(WorkItemProperties.INPUT);
        Objects.requireNonNull(input);
        
        //EXECUTE
        DocumentHeader header = documentRepository.createDocumentHeader(input);
        header.setStatus(InvoiceDocumentStatus.LOADED);

        //OUTPUT
        Map<String, Object> results = workItem.getResults();
        results.put(WorkItemProperties.DOCUMENT_HEADER_RESULT, header);
        manager.completeWorkItem(workItem.getId(), results);
    }

}

package pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler;

import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;

import pl.softfly.oipf.document.validation.schema.DocumentValidationSchema;
import pl.softfly.oipf.document.validation.schema.DocumentValidationSchemaBean;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.samples.invoice.entity.InvoiceDocumentStatus;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ValidateSchemaDocumentWorkItemHandler extends BaseWorkItemHandler implements WorkItemHandler {

    DocumentValidationSchema documentValidationSchema = new DocumentValidationSchemaBean();

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
    	//INPUT
        DocumentHeader documentHeader = (DocumentHeader) workItem.getParameter(WorkItemProperties.DOCUMENT_HEADER_INPUT);
        Objects.requireNonNull(documentHeader);
        
        //EXECUTE
        List<?> list = documentValidationSchema.validate(documentHeader);
        boolean isValid = list == null || list.isEmpty();
		documentHeader.setStatus(isValid ? InvoiceDocumentStatus.SCHEME_VALIDATED : InvoiceDocumentStatus.NOT_SCHEME_VALIDATED);
        
        //OUTPUT
        Map<String, Object> results = workItem.getResults();
        results.put(WorkItemProperties.DOCUMENT_HEADER_RESULT, documentHeader);
        results.put(WorkItemProperties.ERROR_LIST, list);
        results.put(WorkItemProperties.RETURN, isValid);
        manager.completeWorkItem(workItem.getId(), results);
    }

}

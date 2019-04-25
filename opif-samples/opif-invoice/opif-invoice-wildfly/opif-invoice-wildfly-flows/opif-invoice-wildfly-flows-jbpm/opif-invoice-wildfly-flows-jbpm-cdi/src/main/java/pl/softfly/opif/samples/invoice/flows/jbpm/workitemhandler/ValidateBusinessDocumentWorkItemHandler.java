package pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import pl.softfly.oipf.document.validation.business.DocumentValidationBusiness;
import pl.softfly.oipf.document.validation.business.DocumentValidationBusinessBean;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.samples.invoice.entity.InvoiceDocumentStatus;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ValidateBusinessDocumentWorkItemHandler extends BaseWorkItemHandler implements WorkItemHandler {

    DocumentValidationBusiness documentBusinessValidationBean = new DocumentValidationBusinessBean();

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
    	//INPUT
        DocumentHeader documentHeader = (DocumentHeader) workItem.getParameter(WorkItemProperties.DOCUMENT_HEADER_INPUT);
        Objects.requireNonNull(documentHeader);
        
        //EXECUTE
        List<?> list = documentBusinessValidationBean.validate(documentHeader);
        boolean isValid = list == null || list.isEmpty();
		documentHeader.setStatus(isValid ? InvoiceDocumentStatus.BUSINESS_VALIDATED : InvoiceDocumentStatus.NOT_BUSINESS_VALIDATED);
		
        //RESULT
        Map<String, Object> results = workItem.getResults();
        results.put(WorkItemProperties.DOCUMENT_HEADER_RESULT, documentHeader);
        results.put(WorkItemProperties.ERROR_LIST, list);
        results.put(WorkItemProperties.RETURN, isValid);
        manager.completeWorkItem(workItem.getId(), results);
    }

}

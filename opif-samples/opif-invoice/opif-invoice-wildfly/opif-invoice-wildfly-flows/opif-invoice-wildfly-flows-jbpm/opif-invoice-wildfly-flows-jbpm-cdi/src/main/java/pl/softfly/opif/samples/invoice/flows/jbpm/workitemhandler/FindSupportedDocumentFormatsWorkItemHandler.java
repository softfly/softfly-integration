package pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import pl.softfly.oipf.endpoint.EndpointBean;
import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Participant;

public class FindSupportedDocumentFormatsWorkItemHandler extends BaseWorkItemHandler implements WorkItemHandler {

	EndpointBean endpointBean = new EndpointBean();
	
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
    	//INPUT
        DocumentHeader documentHeader = (DocumentHeader) workItem.getParameter(WorkItemProperties.DOCUMENT_HEADER_INPUT);
        Objects.requireNonNull(documentHeader);
        Participant recipient = (Participant) workItem.getParameter(WorkItemProperties.PARTICIPANT_INPUT);
        Objects.requireNonNull(recipient);
        
        //EXECUTE
        List<DictDocumentFormat> supportedDocumentFormatList = endpointBean.findSupportedDocumentFormat(documentHeader, recipient);
        
        //RESULT
        Map<String, Object> results = workItem.getResults();
        results.put(WorkItemProperties.PARTICIPANT_SUPPORTED_DOCUMENT_FORMAT_LIST_RESULT, supportedDocumentFormatList);
        manager.completeWorkItem(workItem.getId(), results);
    }
}

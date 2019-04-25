package pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import pl.softfly.oipf.document.transformation.DocumentTransformationBean;
import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentHeader;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TransformDocumentWorkItemHandler extends BaseWorkItemHandler implements WorkItemHandler {

	DocumentTransformationBean documentTransformation = new DocumentTransformationBean();

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
    	//INPUT
        DocumentHeader documentHeader = (DocumentHeader) workItem.getParameter(WorkItemProperties.DOCUMENT_HEADER_INPUT);
        Objects.requireNonNull(documentHeader);
        List<DictDocumentFormat> targetDocumentFormatList = (List<DictDocumentFormat>) workItem.getParameter(WorkItemProperties.DOCUMENT_FORMAT_LIST_INPUT);
        Objects.requireNonNull(targetDocumentFormatList);
        
        //EXECUTE
        for (DictDocumentFormat targetDocumentFormat: targetDocumentFormatList) {
        	documentTransformation.transform(documentHeader, targetDocumentFormat);
        }
        
        //RESULT
        Map<String, Object> results = workItem.getResults();
        results.put(WorkItemProperties.DOCUMENT_HEADER_RESULT, documentHeader);
        manager.completeWorkItem(workItem.getId(), results);
    }

}

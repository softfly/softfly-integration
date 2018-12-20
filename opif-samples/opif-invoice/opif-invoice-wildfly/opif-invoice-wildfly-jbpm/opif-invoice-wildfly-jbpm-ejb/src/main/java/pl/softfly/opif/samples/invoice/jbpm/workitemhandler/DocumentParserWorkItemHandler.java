package pl.softfly.opif.samples.invoice.jbpm.workitemhandler;

import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import pl.softfly.oipf.document.parser.DocumentParserBean;
import pl.softfly.oipf.entity.DocumentHeader;

import java.util.HashMap;

public class DocumentParserWorkItemHandler implements WorkItemHandler {

    DocumentParserBean documentParserBean = new DocumentParserBean();

    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        manager.abortWorkItem(workItem.getId());
    }

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        DocumentHeader documentHeader = (DocumentHeader) workItem.getParameter("input1");

        documentParserBean.parse(documentHeader);

        HashMap<String, Object> results = new HashMap<String, Object>();
        results.put("output1", documentHeader);

        manager.completeWorkItem(workItem.getId(), results);
    }

}

package pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler;

import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.opif.document.create.TransformStringToDocumentBean;

import java.util.HashMap;

public class TransformStringToDocumentWorkItemHandler implements WorkItemHandler {

    TransformStringToDocumentBean transformStringToDocumentBean = new TransformStringToDocumentBean();

    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        manager.abortWorkItem(workItem.getId());
    }

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        String input = (String) workItem.getParameter("input1");
        System.out.println("test" + input);

        DocumentHeader header = transformStringToDocumentBean.transformToHeader(input);

        HashMap<String, Object> results = new HashMap<String, Object>();
        results.put("output1", header);

        manager.completeWorkItem(workItem.getId(), results);
    }

}

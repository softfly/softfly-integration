package pl.softfly.opif.samples.invoice.jbpm.workitemhandler;

import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import pl.softfly.oipf.document.transformation.DocumentTransformationBean;
import pl.softfly.oipf.endpoint.EndpointBean;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;

import java.util.HashMap;

public class DocumentSendWorkItemHandler implements WorkItemHandler {

    DocumentTransformationBean documentTransformation = new DocumentTransformationBean();

    EndpointBean endpointBean = new EndpointBean();


    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        manager.abortWorkItem(workItem.getId());
    }

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        DocumentHeader documentHeader = (DocumentHeader) workItem.getParameter("documentHeader");
        Object o = workItem.getParameter("recipient");
        Participant recipient = (Participant) workItem.getParameter("recipient");

        // 5. Determine the endpoint of recipient to deliver the document.
        for (Endpoint endpoint : recipient.getEndpoints()) {
            // Znajdź endpoint, dla którego można utworzyć dokument w odpowiednim formacie
            DocumentBody afterTransformDocumentBody = documentTransformation.transform(documentHeader,
                    endpoint.getDictDocumentFormat());
            // 8. Wyślij dokument do odbiorcy.
            if (endpointBean.send(afterTransformDocumentBody, endpoint)) {
                // 9. Powtórz krok 5,6 dla kolejnych odbiorców.
                break;
            }
        }

        HashMap<String, Object> results = new HashMap<String, Object>();
        results.put("documentHeader", documentHeader);

        manager.completeWorkItem(workItem.getId(), results);
    }

}

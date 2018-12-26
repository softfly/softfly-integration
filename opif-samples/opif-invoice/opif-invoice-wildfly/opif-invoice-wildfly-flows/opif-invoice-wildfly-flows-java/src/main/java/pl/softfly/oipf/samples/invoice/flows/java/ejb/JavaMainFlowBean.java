package pl.softfly.oipf.samples.invoice.flows.java.ejb;

import pl.softfly.oipf.document.parser.DocumentParserBean;
import pl.softfly.oipf.document.recognize.DocumentRecognizeBean;
import pl.softfly.oipf.document.transformation.DocumentTransformationBean;
import pl.softfly.oipf.document.validation.business.DocumentBusinessValidationBean;
import pl.softfly.oipf.document.validation.scheme.DocumentValidationSchemeBean;
import pl.softfly.oipf.endpoint.EndpointBean;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Session Bean implementation class MainFlow
 */
@Stateless
@LocalBean
public class JavaMainFlowBean implements MainFlow {

    @EJB
    DocumentRecognizeBean documentRecognize;

    @EJB
    DocumentValidationSchemeBean documentValidationScheme;

    @EJB
    DocumentBusinessValidationBean documentBusinessValidation;

    @EJB
    DocumentParserBean documentParser;

    @EJB
    DocumentTransformationBean documentTransformation;

    @EJB
    EndpointBean endpointBean;

    /**
     * Default constructor.
     */
    public JavaMainFlowBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public DocumentHeader start(String inputDocument) {
        DocumentBody documentBody = new DocumentBody();
        documentBody.setBody(inputDocument);
        receive(documentBody);
        return documentBody.getDocumentHeader();
    }

    protected boolean receive(DocumentBody documentBody) {
        // 1. Rozpoznaj format dokumentu.
        documentBody.setDocumentFormat(documentRecognize.recognize(documentBody));
        // 2. Walidacja synaktyczną dokumentu.
        List<?> validationsScheme = documentValidationScheme.valid(documentBody);
        if (validationsScheme == null || validationsScheme.isEmpty()) {
            // 3. Walidacja biznesowa dokumentu.
            List<?> businessValidations = documentBusinessValidation.valid(documentBody);
            if (businessValidations == null || businessValidations.isEmpty()) {
                // Uzupełnij encje o informacje o dokumencie.
                documentParser.parse(documentBody);
                return process(documentBody);
            }
        }
        return false;
    }

    protected boolean process(DocumentBody documentBody) {
        // 4. Dermine recipients of document.
        List<Participant> recipients = documentBody.getDocumentHeader().getRecipients();
        PARTICIPANT:
        for (Participant recipient : recipients) {
            send(documentBody, recipient);
        }
        return true;
    }

    protected boolean send(DocumentBody documentBody, Participant recipient) {
        // 5. Determine the endpoint of recipient to deliver the document.
        for (Endpoint endpoint : recipient.getEndpoints()) {
            // Znajdź endpoint, dla którego można utworzyć dokument w odpowiednim formacie
            DocumentBody afterTransformDocumentBody = documentTransformation.transform(documentBody,
                    endpoint.getDictDocumentFormat());
            // 8. Wyślij dokument do odbiorcy.
            if (endpointBean.send(afterTransformDocumentBody, endpoint)) {
                // 9. Powtórz krok 5,6 dla kolejnych odbiorców.
                return true;
            }
        }
        return false;
    }

}

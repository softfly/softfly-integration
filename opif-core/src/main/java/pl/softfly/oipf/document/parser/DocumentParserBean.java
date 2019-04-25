package pl.softfly.oipf.document.parser;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

public class DocumentParserBean implements DocumentParser {

    private final static Logger LOGGER = Logger.getLogger(DocumentParserBean.class.getName());

    @Override
    public DocumentBody parse(DocumentBody documentBody) {
    	LOGGER.info(LoggerUtil.start());
        Objects.requireNonNull(documentBody);

        documentBody.setDocumentHeader(newDocumentHeader());

        LOGGER.info(LoggerUtil.end());
        return documentBody;
    }

    @Override
    public DocumentHeader parse(DocumentHeader documentHeader) {
    	LOGGER.info(LoggerUtil.start());
        Objects.requireNonNull(documentHeader);
        
        documentHeader.setRecipients(Arrays.asList(newParticipant(), newParticipant()));

        LOGGER.info(LoggerUtil.end());
        return documentHeader;
    }
    
    private DocumentHeader newDocumentHeader() {
        DocumentHeader header = new DocumentHeader();
        header.setRecipients(Arrays.asList(newParticipant(), newParticipant()));
        return header;
    }
    
    private Participant newParticipant() {
        Participant p = new Participant();
        p.setEndpoints(Arrays.asList(newEndpoint()));
        return p;
    }
    
    private Endpoint newEndpoint() {
        Endpoint e = new Endpoint();
        e.setDictDocumentFormat(newDictDocumentFormat());
        return e;
    }
    
    private DictDocumentFormat newDictDocumentFormat() {
        DictDocumentFormat documentFormat = new DictDocumentFormat();
        documentFormat.setName("G2_INVOICE_3.0");
        return documentFormat;
    } 

}

package pl.softfly.oipf.document.parser;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.Arrays;
import java.util.logging.Logger;

public class DocumentParserBean implements DocumentParser {

    private final static Logger LOGGER = Logger.getLogger(DocumentParserBean.class.getName());

    @Override
    public DocumentBody parse(DocumentBody documentBody) {
        LoggerUtil.start(LOGGER);

        DocumentHeader header = new DocumentHeader();
        Participant p1 = new Participant();
        p1.setEndpoints(Arrays.asList(new Endpoint()));
        Participant p2 = new Participant();
        p2.setEndpoints(Arrays.asList(new Endpoint()));
        header.setRecipients(Arrays.asList(p1, p2));
        documentBody.setDocumentHeader(header);

        LoggerUtil.end(LOGGER);
        return documentBody;
    }
    
    @Override
    public DocumentHeader parse(DocumentHeader documentHeader) {
        LoggerUtil.start(LOGGER);

        Participant p1 = new Participant();
        p1.setEndpoints(Arrays.asList(new Endpoint()));
        Participant p2 = new Participant();
        p2.setEndpoints(Arrays.asList(new Endpoint()));
        documentHeader.setRecipients(Arrays.asList(p1, p2));

        LoggerUtil.end(LOGGER);
        return documentHeader;
    }

}

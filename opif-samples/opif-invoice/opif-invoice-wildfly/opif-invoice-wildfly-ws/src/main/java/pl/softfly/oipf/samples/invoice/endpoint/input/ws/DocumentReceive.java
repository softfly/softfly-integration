package pl.softfly.oipf.samples.invoice.endpoint.input.ws;

import pl.softfly.oipf.entity.DocumentHeader;

public interface DocumentReceive {

    DocumentHeader start(String inputDocument);

}

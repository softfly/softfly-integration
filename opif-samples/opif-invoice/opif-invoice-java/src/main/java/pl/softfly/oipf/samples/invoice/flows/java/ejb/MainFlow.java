package pl.softfly.oipf.samples.invoice.flows.java.ejb;

import pl.softfly.oipf.entity.DocumentHeader;

public interface MainFlow {

	JavaMainFlowResponse start(String inputDocument);

}

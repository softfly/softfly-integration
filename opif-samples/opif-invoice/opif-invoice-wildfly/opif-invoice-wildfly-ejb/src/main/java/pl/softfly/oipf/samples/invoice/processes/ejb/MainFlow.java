package pl.softfly.oipf.samples.invoice.processes.ejb;

import pl.softfly.oipf.entity.DocumentHeader;

public interface MainFlow {

    DocumentHeader start(String inputDocument);

}

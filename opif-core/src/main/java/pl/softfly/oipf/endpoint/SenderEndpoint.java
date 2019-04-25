package pl.softfly.oipf.endpoint;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Participant;

public interface SenderEndpoint {

	/**
	 * Try to find endpoint for participant documentFormat and send.
	 *
	 * @param documentHeader
	 * @param recipient
	 * @return
	 */
    boolean send(DocumentHeader documentHeader, Participant recipient);

    /**
     * Send document via endpoint.
     *
     * @param documentBody
     * @param endpoint
     * @return
     */
    boolean send(DocumentBody documentBody, pl.softfly.oipf.entity.Endpoint endpoint);
}

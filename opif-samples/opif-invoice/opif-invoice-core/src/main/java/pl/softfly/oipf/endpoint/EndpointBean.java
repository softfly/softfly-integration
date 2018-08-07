package pl.softfly.oipf.endpoint;

import org.apache.commons.lang3.StringUtils;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.Participant;

import java.util.logging.Logger;

public class EndpointBean {

    private final static Logger LOGGER = Logger.getLogger(EndpointBean.class.getName());

    public boolean send(DocumentBody documentBody, Participant participant) {
        LOGGER.info(StringUtils.center("START", 30, "="));
        LOGGER.info(StringUtils.center("END", 30, "="));
        return true;
    }
}

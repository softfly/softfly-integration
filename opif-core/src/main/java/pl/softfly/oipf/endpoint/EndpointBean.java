package pl.softfly.oipf.endpoint;

import org.apache.commons.lang3.StringUtils;
import pl.softfly.oipf.entity.DocumentBody;

import java.util.logging.Logger;

public class EndpointBean implements Endpoint {

    private final static Logger LOGGER = Logger.getLogger(EndpointBean.class.getName());

    @Override
    public boolean send(DocumentBody documentBody, pl.softfly.oipf.entity.Endpoint endpoint) {
        LOGGER.info(StringUtils.center("START", 30, "="));
        LOGGER.info(StringUtils.center("END", 30, "="));
        return true;
    }
}

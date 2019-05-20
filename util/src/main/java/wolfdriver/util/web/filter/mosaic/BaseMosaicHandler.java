package wolfdriver.util.web.filter.mosaic;

import java.util.Map;

public abstract class BaseMosaicHandler implements MosaicHandler {

    @Override
    public Map handleParameters(Map params) {
        return params;
    }

    @Override
    public String handleResponse(String uri, String body) {
        return body;
    }

    @Override
    public String handleRequest(String uri, String body) {
        return body;
    }
}

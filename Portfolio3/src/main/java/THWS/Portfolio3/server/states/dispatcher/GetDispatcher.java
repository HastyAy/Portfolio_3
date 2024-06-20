package THWS.Portfolio3.server.states.dispatcher;

import THWS.Portfolio3.server.URLS;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GetDispatcher {

    public Map<String, String> getInitialLinks() {
        URLS url = new URLS();
        Map<String, String> links = new HashMap<>();
        links.put("universities", url.getDispatcher()+"/universities");
        return links;
    }
}

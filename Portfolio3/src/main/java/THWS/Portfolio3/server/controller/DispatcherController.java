package THWS.Portfolio3.server.controller;

import THWS.Portfolio3.server.URLS;
import THWS.Portfolio3.server.states.dispatcher.GetDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DispatcherController {
    @Autowired
    private GetDispatcher getDispatcher;

    @GetMapping
    public Map<String, String> getInitialLinks() {
        return getDispatcher.getInitialLinks();
    }
}

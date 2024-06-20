package THWS.Portfolio3.client;

import THWS.Portfolio3.client.models.ModuleClientModel;
import THWS.Portfolio3.client.models.UniversityClientModel;
import THWS.Portfolio3.server.URLS;
import THWS.Portfolio3.server.states.dispatcher.GetDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RestClient {
     static URLS url = new URLS();
    private static final String BASE_URL = "http://localhost:8080";
    private static final String DISPATCHER_URL = url.getDispatcher();
    private final RestTemplate restTemplate;

    private final GetDispatcher getDispatcher;
    private Map<String, String> initialLinks;
    private static final Logger logger = Logger.getLogger(RestClient.class.getName());

    @Autowired
    public RestClient(RestTemplate restTemplate, Traverson traverson, GetDispatcher getDispatcher) {
        this.restTemplate = restTemplate;
        this.getDispatcher = getDispatcher;
    }

    @PostConstruct
    public void init() {
        boolean success = false;
        int attempts = 0;
        int maxAttempts = 5;

        while (!success && attempts < maxAttempts) {
            try {
                initialLinks = getInitialLinks();
                success = true;
            } catch (Exception e) {
                attempts++;
                logger.log(Level.WARNING, "Failed to initialize initial links, retrying... (" + attempts + "/" + maxAttempts + ")", e);
                if (attempts < maxAttempts) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ignored) {
                    }
                } else {
                    throw new RuntimeException("Failed to fetch initial links from dispatcher", e);
                }
            }
        }
    }

    public Map<String, String> getInitialLinks() {
        return getDispatcher.getInitialLinks();
    }

    public EntityModel<UniversityClientModel> createUniversity(UniversityClientModel university) {
        logger.info("Creating university with initial links: " + initialLinks);
        ResponseEntity<EntityModel<UniversityClientModel>> response = restTemplate.exchange(
                BASE_URL + initialLinks.get("universities"),
                HttpMethod.POST,
                new HttpEntity<>(university),
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public PagedModel<EntityModel<UniversityClientModel>> getAllUniversities(Pageable pageable) {
        ResponseEntity<PagedModel<EntityModel<UniversityClientModel>>> response = restTemplate.exchange(
                BASE_URL + initialLinks.get("universities") + "?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public EntityModel<UniversityClientModel> getUniversityById(Long id) {
        ResponseEntity<EntityModel<UniversityClientModel>> response = restTemplate.exchange(
                BASE_URL + initialLinks.get("universities") + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public EntityModel<UniversityClientModel> updateUniversity(Long id, UniversityClientModel university) {
        restTemplate.exchange(
                BASE_URL + initialLinks.get("universities") + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(university),
                Void.class
        );
        return getUniversityById(id);
    }

    public void deleteUniversity(Long id) {
        restTemplate.exchange(
                BASE_URL + initialLinks.get("universities") + "/" + id,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

    public EntityModel<ModuleClientModel> createModule(Long universityId, ModuleClientModel module) {
        ResponseEntity<EntityModel<ModuleClientModel>> response = restTemplate.exchange(
                BASE_URL + DISPATCHER_URL + "/universities/" + universityId + "/modules",
                HttpMethod.POST,
                new HttpEntity<>(module),
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public PagedModel<EntityModel<ModuleClientModel>> getAllModules(Long universityId) {
        ResponseEntity<PagedModel<EntityModel<ModuleClientModel>>> response = restTemplate.exchange(
                BASE_URL + DISPATCHER_URL + "/universities/" + universityId + "/modules",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public EntityModel<ModuleClientModel> getModuleById(Long universityId, Long moduleId) {
        ResponseEntity<EntityModel<ModuleClientModel>> response = restTemplate.exchange(
                BASE_URL + DISPATCHER_URL + "/universities/" + universityId + "/modules/" + moduleId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public void deleteModule(Long universityId, Long moduleId) {
        restTemplate.exchange(
                BASE_URL + DISPATCHER_URL + "/universities/" + universityId + "/modules/" + moduleId,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

    public EntityModel<ModuleClientModel> updateModule(Long universityId, Long moduleId, ModuleClientModel module) {
        ResponseEntity<EntityModel<ModuleClientModel>> response = restTemplate.exchange(
                BASE_URL + DISPATCHER_URL + "/universities/" + universityId + "/modules/" + moduleId,
                HttpMethod.PUT,
                new HttpEntity<>(module),
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}

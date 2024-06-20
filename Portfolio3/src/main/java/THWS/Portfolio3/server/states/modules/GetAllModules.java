package THWS.Portfolio3.server.states.modules;

import THWS.Portfolio3.server.model.Module;
import THWS.Portfolio3.server.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllModules {

    private final ModuleRepository moduleRepository;

    @Autowired
    public GetAllModules(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<Module> execute(Long universityId) {
        return moduleRepository.findByUniversityId(universityId);
    }
}

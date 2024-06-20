package THWS.Portfolio3.server.states.modules;

import THWS.Portfolio3.server.exception.ResourceNotFoundException;
import THWS.Portfolio3.server.model.Module;
import THWS.Portfolio3.server.model.University;
import THWS.Portfolio3.server.repository.ModuleRepository;
import THWS.Portfolio3.server.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class PostNewModule {

    private final ModuleRepository moduleRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public PostNewModule(ModuleRepository moduleRepository, UniversityRepository universityRepository) {
        this.moduleRepository = moduleRepository;
        this.universityRepository = universityRepository;
    }

    public EntityModel<Module> execute(Long universityId, Module module) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));

        module.setUniversity(university);
        Module savedModule = moduleRepository.save(module);
        return EntityModel.of(savedModule);
    }
}


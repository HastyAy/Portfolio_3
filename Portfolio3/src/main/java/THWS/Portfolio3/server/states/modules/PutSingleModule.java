package THWS.Portfolio3.server.states.modules;

import THWS.Portfolio3.server.model.Module;
import THWS.Portfolio3.server.repository.ModuleRepository;
import THWS.Portfolio3.server.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class PutSingleModule {

    private final ModuleRepository moduleRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public PutSingleModule(ModuleRepository moduleRepository, UniversityRepository universityRepository) {
        this.moduleRepository = moduleRepository;
        this.universityRepository = universityRepository;
    }

    public EntityModel<Module> execute(Long universityId, Long moduleId, Module moduleDetails) {
        universityRepository.findById(universityId).orElseThrow(() -> new RuntimeException("University not found"));
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        module.setName(moduleDetails.getName());
        module.setModuleName(moduleDetails.getModuleName());
        module.setSemester(moduleDetails.getSemester());
        module.setSemesterOffered(moduleDetails.getSemesterOffered());
        module.setCreditPoints(moduleDetails.getCreditPoints());

        Module updatedModule = moduleRepository.save(module);
        return EntityModel.of(updatedModule);
    }
}

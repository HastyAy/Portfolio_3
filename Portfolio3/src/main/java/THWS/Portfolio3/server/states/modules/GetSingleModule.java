package THWS.Portfolio3.server.states.modules;

import THWS.Portfolio3.server.model.Module;
import THWS.Portfolio3.server.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetSingleModule {

    private final ModuleRepository moduleRepository;

    @Autowired
    public GetSingleModule(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public Optional<Module> execute(Long universityId, Long id) {
        return moduleRepository.findByIdAndUniversityId(id, universityId);
    }
}

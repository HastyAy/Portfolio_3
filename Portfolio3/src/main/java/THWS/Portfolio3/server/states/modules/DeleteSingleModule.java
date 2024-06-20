package THWS.Portfolio3.server.states.modules;

import THWS.Portfolio3.server.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteSingleModule {

    private final ModuleRepository moduleRepository;

    @Autowired
    public DeleteSingleModule(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public void execute(Long moduleId) {
        moduleRepository.deleteById(moduleId);
    }
}
